package com.sibvisions.module.user.screens;

import com.sibvisions.apps.components.NavigationTable;
import com.sibvisions.apps.projx.ProjXUtil;
import com.sibvisions.apps.projx.screens.DataSourceWorkScreen;
import com.sibvisions.rad.model.remote.RemoteDataBook;
import java.util.Map;
import javax.rad.application.IWorkScreenApplication;
import javax.rad.genui.UIColor;
import javax.rad.genui.UIDimension;
import javax.rad.genui.UIInsets;
import javax.rad.genui.component.UIButton;
import javax.rad.genui.component.UILabel;
import javax.rad.genui.container.UIGroupPanel;
import javax.rad.genui.container.UIPanel;
import javax.rad.genui.control.UIEditor;
import javax.rad.genui.layout.UIBorderLayout;
import javax.rad.genui.layout.UIFormLayout;
import javax.rad.model.ColumnView;
import javax.rad.model.SortDefinition;
import javax.rad.model.event.DataBookEvent;
import javax.rad.model.event.DataRowEvent;
import javax.rad.model.ui.ITableControl;
import javax.rad.remote.AbstractConnection;
import javax.rad.ui.event.UIActionEvent;

/**
 * The New screen manages ...
 */
public class ImportWorkViewsWorkScreen extends DataSourceWorkScreen
{

	/** editImportworkviewsMenu. */
	private UIEditor		editImportworkviewsMenu		= new UIEditor();

	/** editImportworkviewsSequence. */
	private UIEditor		editImportworkviewsSequence	= new UIEditor();

	/** labelMenu. */
	private UILabel			labelMenu					= new UILabel();

	/** labelSequence. */
	private UILabel			labelSequence				= new UILabel();

	/** groupPanelImportViews. */
	private UIGroupPanel	groupPanelImportViews		= new UIGroupPanel();

	/** groupPanelImportworkviews. */
	private UIGroupPanel	groupPanelImportworkviews	= new UIGroupPanel();

	/** groupPanelInstalledViews. */
	private UIGroupPanel	groupPanelInstalledViews	= new UIGroupPanel();

	/** buttonButton1. */
	private UIButton		buttonButton1				= new UIButton();

	/** buttonButton. */
	private UIButton		buttonButton				= new UIButton();

	/** buttonButton3. */
	private UIButton		buttonButton3				= new UIButton();

	/** buttonButton5. */
	private UIButton		buttonButton5				= new UIButton();

	/** buttonButton4. */
	private UIButton		buttonButton4				= new UIButton();

	/** buttonButton2. */
	private UIButton		buttonButton2				= new UIButton();

	/** tableImportworkviews. */
	private NavigationTable	tableImportworkviews		= new NavigationTable();

	/** tableWorkscreens. */
	private NavigationTable	tableWorkscreens			= new NavigationTable();

	/** formLayout1. */
	private UIFormLayout	formLayout1					= new UIFormLayout();

	/** formLayout3. */
	private UIFormLayout	formLayout3					= new UIFormLayout();

	/** formLayout2. */
	private UIFormLayout	formLayout2					= new UIFormLayout();

	/** formLayout4. */
	private UIFormLayout	formLayout4					= new UIFormLayout();

	/** formLayout5. */
	private UIFormLayout	formLayout5					= new UIFormLayout();

	/** panelMain. */
	private UIPanel			panelMain					= new UIPanel();

	/** panel2. */
	private UIPanel			panel2						= new UIPanel();

	/** layoutThis. */
	private UIBorderLayout	layoutThis					= new UIBorderLayout();

	/** Remote databook importworkviews. */
	private RemoteDataBook	rdbImportworkviews			= new RemoteDataBook();

	/** Remote databook workscreens. */
	private RemoteDataBook	rdbWorkscreens				= new RemoteDataBook();

	String					applicationName				= "BricksJaume2";

	/**
	 * Constructs a new instance of <code>ImportWorkViewsWorkScreen</code>.
	 * 
	 * @param pApplication the application
	 * @param pConnection the connection
	 * @param pParameter additional screen parameters
	 * @throws Throwable if an error occurs
	 */
	public ImportWorkViewsWorkScreen(IWorkScreenApplication pApplication, AbstractConnection pConnection, Map<String, Object> pParameter) throws Throwable
	{
		super(pApplication, pConnection, pParameter);

		initializeModel();
		initializeUI();
	}

	/**
	 * Initializes the model.
	 * 
	 * @throws Throwable if the initialization throws an error
	 */
	private void initializeModel() throws Throwable
	{
		rdbImportworkviews.setName("importworkviews");
		rdbImportworkviews.setDataSource(getDataSource());
		rdbImportworkviews.open();
		rdbImportworkviews.getRowDefinition().getColumnDefinition("FILENAME").setLabel("FileName");

		rdbImportworkviews.getRowDefinition().getColumnDefinition("CLASSPATH").setLabel("ClassPath");

		rdbImportworkviews.getRowDefinition().getColumnDefinition("ENV_DESKTOP").setLabel("ENV_DESKTOP");

		rdbImportworkviews.getRowDefinition().getColumnDefinition("ENV_DESKTOP").getDataType().setCellEditor(ProjXUtil.YESNO_EDITOR);

		rdbImportworkviews.getRowDefinition().getColumnDefinition("ENV_MOBILE").setLabel("ENV_MOBILE");

		rdbImportworkviews.getRowDefinition().getColumnDefinition("ENV_MOBILE").getDataType().setCellEditor(ProjXUtil.YESNO_EDITOR);

		rdbImportworkviews.getRowDefinition().getColumnDefinition("ENV_WEB").setLabel("ENV_WEB");

		rdbImportworkviews.getRowDefinition().getColumnDefinition("ENV_WEB").getDataType().setCellEditor(ProjXUtil.YESNO_EDITOR);

		rdbImportworkviews.getRowDefinition().setColumnView(ITableControl.class, new ColumnView(new String[] { "FILENAME" }));

		rdbWorkscreens.setName("workscreens");
		rdbWorkscreens.setDataSource(getDataSource());
		rdbWorkscreens.setSort(new SortDefinition(false, "TEXT"));

		rdbWorkscreens.open();
		rdbWorkscreens.eventAfterInserting().addListener(this::doWorkscreensAfterInserting);
		rdbWorkscreens.eventValuesChanged().addListener(this::doWorkscreensValuesChanged);

		rdbWorkscreens.getRowDefinition().getColumnDefinition("SHORTCUT").getDataType().setCellEditor(ProjXUtil.YESNO_EDITOR);

		rdbWorkscreens.getRowDefinition().getColumnDefinition("ENV_DESKTOP").getDataType().setCellEditor(ProjXUtil.YESNO_EDITOR);

		rdbWorkscreens.getRowDefinition().getColumnDefinition("ENV_WEB").getDataType().setCellEditor(ProjXUtil.YESNO_EDITOR);

		rdbWorkscreens.getRowDefinition().getColumnDefinition("ENV_MOBILE").getDataType().setCellEditor(ProjXUtil.YESNO_EDITOR);

		rdbWorkscreens.getRowDefinition().setColumnView(ITableControl.class, new ColumnView(new String[] { "TEXT", "ENABLED" }));

	}

	/**
	 * Initializes the UI.
	 * 
	 * @throws Throwable if the initialization throws an error
	 */
	private void initializeUI() throws Throwable
	{
		tableWorkscreens.setCellFormatter(this, "formatLog");


		layoutThis.setMargins(new UIInsets(5, 5, 5, 5));

		formLayout1.setAnchorConfiguration("r0=432,l1=28,r-2=-93,b0=-5,b1=-5,b2=434");
		formLayout1.setMargins(new UIInsets(10, 10, 10, 57));

		formLayout3.setMargins(new UIInsets(241, 43, 10, 10));

		buttonButton1.setText("Pull from repository");
		buttonButton1.eventAction().addListener(this::doButtonbutton1);

		buttonButton.setText("Import View");
		buttonButton.eventAction().addListener(this::doButtonbutton);

		buttonButton3.setText("Export view");
		buttonButton3.eventAction().addListener(this::doButtonbutton3);

		buttonButton5.setText("Disable view");
		buttonButton5.eventAction().addListener(this::doButtonbutton5);

		buttonButton4.setText("Enable view");
		buttonButton4.eventAction().addListener(this::doButtonbutton4);

		buttonButton2.setText("Delete View");
		buttonButton2.eventAction().addListener(this::doButtonbutton2);

		tableImportworkviews.setToolBarVisible(false);
		tableImportworkviews.setMaximumSize(new UIDimension(450, 350));
		tableImportworkviews.setExportVisible(false);
		tableImportworkviews.setDataBook(rdbImportworkviews);
		tableImportworkviews.setAutoResize(false);

		tableWorkscreens.setToolBarVisible(false);
		tableWorkscreens.setMaximumSize(new UIDimension(450, 350));
		tableWorkscreens.setExportVisible(false);
		tableWorkscreens.setDataBook(rdbWorkscreens);
		tableWorkscreens.setAutoResize(false);

		formLayout2.setMargins(new UIInsets(10, 10, 10, 45));
		formLayout2.setAnchorConfiguration("b0=201");

		formLayout4.setMargins(new UIInsets(10, 10, 10, 44));
		formLayout5.setMargins(new UIInsets(10, 10, 68, 27));
		formLayout5.setAnchorConfiguration("r0=73,l1=43,l2=31,b0=201");

		labelMenu.setText("Menu");

		labelSequence.setText("Sequence");

		editImportworkviewsMenu.setDataRow(rdbImportworkviews);
		editImportworkviewsMenu.setColumnName("MENU");

		editImportworkviewsSequence.setDataRow(rdbImportworkviews);
		editImportworkviewsSequence.setColumnName("SEQUENCE");

		groupPanelImportworkviews.setText("");
		groupPanelImportworkviews.setLayout(formLayout4);
		groupPanelImportworkviews.add(labelMenu, formLayout4.getConstraints(0, 0));
		groupPanelImportworkviews.add(editImportworkviewsMenu, formLayout4.getConstraints(1, 0));
		groupPanelImportworkviews.add(buttonButton1, formLayout4.getVCenterConstraints(-1, 0));
		groupPanelImportworkviews.add(labelSequence, formLayout4.getConstraints(0, 1));
		groupPanelImportworkviews.add(editImportworkviewsSequence, formLayout4.getConstraints(1, 1));
		groupPanelImportworkviews.add(buttonButton, formLayout4.getVCenterConstraints(-1, 1));

		panel2.setLayout(formLayout3);

		groupPanelInstalledViews.setText("Installed views");
		groupPanelInstalledViews.setLayout(formLayout5);
		groupPanelInstalledViews.add(tableWorkscreens, formLayout5.getConstraints(0, 0, -1, 0));
		groupPanelInstalledViews.add(buttonButton3, formLayout5.getConstraints(0, 1));
		groupPanelInstalledViews.add(buttonButton5, formLayout5.getConstraints(1, 1));
		groupPanelInstalledViews.add(buttonButton4, formLayout5.getConstraints(2, 1));
		groupPanelInstalledViews.add(buttonButton2, formLayout5.getConstraints(-1, 1));

		groupPanelImportViews.setText("Import Views");
		groupPanelImportViews.setLayout(formLayout2);
		groupPanelImportViews.add(tableImportworkviews, formLayout2.getConstraints(0, 0, -1, 0));
		groupPanelImportViews.add(groupPanelImportworkviews, formLayout2.getConstraints(0, 1, -1, 1));

		panelMain.setLayout(formLayout1);
		panelMain.add(groupPanelImportViews, formLayout1.getConstraints(0, 2, 0, -1));
		panelMain.add(groupPanelInstalledViews, formLayout1.getConstraints(1, 0, -2, 2));
		panelMain.add(panel2, formLayout1.getConstraints(-1, 1, -1, -1));

		setLayout(layoutThis);
		add(panelMain, UIBorderLayout.CENTER);
	}

	/**
	 * Button.
	 */
	public void doButtonbutton(UIActionEvent pEvent) throws Throwable
	{
		rdbImportworkviews.reload();
		getConnection().callAction("ExportScreenToDB", rdbImportworkviews.getValue("ID"));
		getConnection().callAction("UpdateJar", (String)rdbImportworkviews.getValue("FILENAME"));
		getDataSource().reloadAllDataBooks();
		showInformation(this, "Screen imported");
		buttonButton2.setBackground(new UIColor(0xff5f59));
	}

	/**
	 * Pull from repository.
	 */
	public void doButtonbutton1(UIActionEvent pEvent) throws Throwable
	{
		rdbImportworkviews.deleteAllRows();
		getConnection().callAction("PullFromRepository");
		getConnection().callAction("LoadFile");
		rdbImportworkviews.reload();
	}

	/**
	 * onLoad ImportWorkViews.
	 */
	public void onLoad() throws Throwable
	{
		rdbImportworkviews.reload();
	}

	/**
	 * Enable view.
	 */
	public void doButtonbutton4(UIActionEvent pEvent) throws Throwable
	{
		getConnection().callAction("EnableView", rdbWorkscreens.getValue("ID"));
		showInformation(this, "Screen enabled");
	}

	/**
	 * Disable view.
	 */
	public void doButtonbutton5(UIActionEvent pEvent) throws Throwable
	{
		getConnection().callAction("DisableView", rdbWorkscreens.getValue("ID"));
		showInformation(this, "Screen disabled");
	}

	/**
	 * Delete View.
	 */
	public void doButtonbutton2(UIActionEvent pEvent) throws Throwable
	{
		getConnection().callAction("DeleteView", rdbWorkscreens.getValue("ID"));
		getConnection().callAction("RemoveFromJar", rdbWorkscreens.getValue("TEXT"));
		getDataSource().reloadAllDataBooks();
		showInformation(this, "Screen deleted");
	}

	/**
	 * Export view.
	 */
	public void doButtonbutton3(UIActionEvent pEvent) throws Throwable
	{
		getConnection().callAction("ExportView", rdbWorkscreens.getValue("TEXT"));
		showInformation(this, "Screen exported");
	}

	/**
	 * Workscreens.
	 */
	public void doWorkscreensAfterInserting(DataBookEvent pEvent) throws Throwable
	{
		getDataSource().reloadAllDataBooks();
	}

	/**
	 * Workscreens.
	 */
	public void doWorkscreensValuesChanged(DataRowEvent pEvent) throws Throwable
	{
		tableWorkscreens.setBackground(new UIColor(0x51ff55));
	}

	/**
	 * Importworkviews.
	 */

} // ImportWorkViewsWorkScreen

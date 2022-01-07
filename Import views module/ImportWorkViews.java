package com.sibvisions.module.user.screens;

import app.visionx.apps.bricksjaume2.Session;

import com.google.gson.Gson;
import com.sibvisions.rad.persist.jdbc.DBAccess;
import com.sibvisions.rad.persist.jdbc.DBStorage;
import com.sibvisions.rad.persist.jdbc.param.InParam;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
* The ImportWorkViews class is the life-cycle object for ImportWorkViewsWorkScreen.
*/
public class ImportWorkViews extends Session
{

	String applicationName = "BricksJaume2";


	/**
	 * Gets the WORKSCREENS database storage.
	 * 
	 * @throws Exception if the DBStorage couldn't initialized.
	 * @return the workscreens DBStorage.
	 */
	public DBStorage getWorkscreens() throws Exception
	{
		DBStorage dbsWorkscreens = (DBStorage)get("workscreens");
		if (dbsWorkscreens == null)
		{
			dbsWorkscreens = new DBStorage();
			dbsWorkscreens.setWritebackTable("WORKSCREENS");
			dbsWorkscreens.setDBAccess(getDBAccess());
			dbsWorkscreens.open();

			put("workscreens", dbsWorkscreens);
		}
		return dbsWorkscreens;
	}

	/**
	 * Gets the IMPORTWORKVIEWS database storage.
	 * 
	 * @throws Exception if the DBStorage couldn't initialized.
	 * @return the importworkviews DBStorage.
	 */
	public DBStorage getImportworkviews() throws Exception
	{
		DBStorage dbsImportworkviews = (DBStorage)get("importworkviews");
		if (dbsImportworkviews == null)
		{
			dbsImportworkviews = new DBStorage();
			dbsImportworkviews.setWritebackTable("IMPORTWORKVIEWS");
			dbsImportworkviews.setDBAccess(getDBAccess());
			dbsImportworkviews.setWhereClause("NOT EXISTS (\n    SELECT 1 \n    FROM workscreens w\n    WHERE m.filename = w.text)");
			dbsImportworkviews.open();
			put("importworkviews", dbsImportworkviews);
		}
		return dbsImportworkviews;
	}

	public void LoadFile() throws Exception
	{
		//		CreateScript();
		//makes dirs if they dont exist
		File myObj2 = new File("webapps\\"+applicationName+"\\ImportViewsModule\\app\\visionx\\apps\\"+applicationName+"\\screens");
		myObj2.mkdirs();

		final File folder = new File("webapps\\"+applicationName+"\\ImportViewsModule\\app\\visionx\\apps\\"+applicationName+"\\screens");
		DBAccess dba = super.getDBAccess();
		InParam ioJsonDataParam;
		Gson gson = new Gson();
		String json = gson.toJson(FilterFile(folder.list()));
		System.out.println(json);

		ioJsonDataParam = new InParam(InParam.SQLTYPE_VARCHAR, json);
		dba.executeProcedure("[dbo].[workscreen_load_files]", ioJsonDataParam);
	}

	public ArrayList<String> FilterFile(String[] list)
	{
		//Filtrem tots els arxius que no siguin .class i que no siguin workscreens.
		ArrayList<String> newList = new ArrayList<>();
		for (int i = 0; i < list.length; i++)
		{
			if (!list[i].contains("WorkScreen") && list[i].contains(".class"))
			{
				newList.add(list[i].replace(".class", ""));
			}
		}
		return newList;

	}

	public void ExportScreenToDB(Object obj) throws Exception
	{

		DBAccess dba = super.getDBAccess();
		InParam ioJsonDataParam;
		BigDecimal id = (BigDecimal)obj;
		ioJsonDataParam = new InParam(InParam.SQLTYPE_DECIMAL, id);
		dba.executeProcedure("[dbo].[workscreen_export_rows]", ioJsonDataParam);

	}

	public void UpdateJar(String screenName) throws IOException, InterruptedException
	{

		Runtime.getRuntime().exec("webapps\\"+applicationName+"\\ImportViewsModule\\ImportView.bat " + screenName);

	}

	public void PullFromRepository() throws IOException, GitAPIException, InterruptedException
	{

		ProcessBuilder builder = new ProcessBuilder("webapps\\"+applicationName+"\\ImportViewsModule\\PullGitLab.bat");
		builder.redirectErrorStream(true);
		Process process = builder.start();
		process.waitFor();


	}
	public void DisableView(Object obj) throws Exception {
		DBAccess dba = super.getDBAccess();
		InParam ioJsonDataParam;
		BigDecimal id = (BigDecimal)obj;
		ioJsonDataParam = new InParam(InParam.SQLTYPE_DECIMAL, id);
		dba.executeProcedure("[dbo].[workscreen_disable_view]", ioJsonDataParam);
	}
	public void EnableView(Object obj) throws Exception {
		DBAccess dba = super.getDBAccess();
		InParam ioJsonDataParam;
		BigDecimal id = (BigDecimal)obj;
		ioJsonDataParam = new InParam(InParam.SQLTYPE_DECIMAL, id);
		dba.executeProcedure("[dbo].[workscreen_enable_view]", ioJsonDataParam);
	}


	public void DeleteView(Object obj) throws Exception {


		//delete row from db
		DBAccess dba = super.getDBAccess();
		InParam ioJsonDataParam;
		BigDecimal id = (BigDecimal)obj;
		ioJsonDataParam = new InParam(InParam.SQLTYPE_DECIMAL, id);
		dba.executeProcedure("[dbo].[workscreen_delete_view]", ioJsonDataParam);


	}

	public void RemoveFromJar(Object obj) throws IOException {
		//delete file from jar
		String className = (String) obj;
		Runtime.getRuntime().exec("webapps\\"+applicationName+"\\ImportViewsModule\\DeleteFromJar.bat " + className);


	}

	public void ExportView(Object obj) throws IOException {
		//delete file from jar
		String className = (String) obj;
		Runtime.getRuntime().exec("webapps\\"+applicationName+"\\ImportViewsModule\\ExportView.bat " + className);


	}

}

// ImportWorkViews

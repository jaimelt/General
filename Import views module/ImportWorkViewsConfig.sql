USE [BricksJaume]
GO

/****** Object:  Table [dbo].[IMPORTWORKVIEWS]    Script Date: 03/01/2022 13:50:31 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[IMPORTWORKVIEWS](
	[ID] [decimal](16, 0) IDENTITY(1,1) NOT NULL,
	[FILENAME] [varchar](255) NOT NULL,
	[CLASSPATH] [varchar](255) NULL,
	[SEQUENCE] [decimal](16, 0) NULL,
	[MENU] [varchar](50) NULL,
	[ENV_DESKTOP] [varchar](1) NULL,
	[ENV_MOBILE] [varchar](1) NULL,
	[ENV_WEB] [varchar](1) NULL,
	[full_classpath]  AS ((([classpath]+'.')+[filename])+'WorkScreen'),
 CONSTRAINT [IMPO_PK] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [filenameunique] UNIQUE NONCLUSTERED 
(
	[FILENAME] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[IMPORTWORKVIEWS] ADD  CONSTRAINT [df_classpath]  DEFAULT ('app.visionx.apps.bricksjaume.screens') FOR [CLASSPATH]
GO

ALTER TABLE [dbo].[IMPORTWORKVIEWS] ADD  CONSTRAINT [df_sequence]  DEFAULT ((0)) FOR [SEQUENCE]
GO

ALTER TABLE [dbo].[IMPORTWORKVIEWS] ADD  CONSTRAINT [df_env_menu]  DEFAULT ('Installed views') FOR [MENU]
GO

ALTER TABLE [dbo].[IMPORTWORKVIEWS] ADD  CONSTRAINT [df_env_desktop]  DEFAULT ('Y') FOR [ENV_DESKTOP]
GO

ALTER TABLE [dbo].[IMPORTWORKVIEWS] ADD  CONSTRAINT [df_env_mobile]  DEFAULT ('Y') FOR [ENV_MOBILE]
GO

ALTER TABLE [dbo].[IMPORTWORKVIEWS] ADD  CONSTRAINT [df_env_web]  DEFAULT ('Y') FOR [ENV_WEB]
GO
/****** Alter Workscren table ******/

ALTER TABLE [dbo].[WORKSCREENS] ADD  DEFAULT ('Y') FOR [ENV_DESKTOP]
GO

ALTER TABLE [dbo].[WORKSCREENS] ADD  DEFAULT ('Y') FOR [ENV_WEB]
GO

ALTER TABLE [dbo].[WORKSCREENS] ADD  DEFAULT ('Y') FOR [ENV_MOBILE]
GO


/******CREATE PROCEDURE EXPORT_ROWS******/
CREATE procedure [dbo].[workscreen_export_rows] @param int

as
insert into [dbo].[WORKSCREENS] (text,MENU_STRUCTURE,SEQUENCE,CLASSNAME,ENV_DESKTOP,ENV_MOBILE,ENV_WEB) 

(select filename,menu,sequence,full_classpath,env_desktop,env_mobile,env_web from IMPORTWORKVIEWS where id = @param)

insert into [dbo].[ROLE_WOSC] (ROLE_ID,WOSC_ID,CREATED_BY,CREATED_ON,CHANGED_BY,CHANGED_ON) 
values (1,
(select id from [dbo].[WORKSCREENS] where text =  (select filename from IMPORTWORKVIEWS where id = @param))
,'admin'
,GETDATE()
,'admin'
,GETDATE())
GO

/******CREATE PROCEDURE LOAD_FILES******/


CREATE procedure [dbo].[workscreen_load_files] @json nvarchar(1000)

as

     		DECLARE @Counter INT 
			SET @Counter=0
		
			WHILE ( @Counter <= (select count(*) from OPENJSON(@json))-1)
BEGIN
  insert into [dbo].[IMPORTWORKVIEWS] (filename)
SELECT value FROM OPENJSON(@json) as x  where x.[key] = @Counter and
 NOT EXISTS ( SELECT * FROM [dbo].[WORKSCREENS]
                   WHERE text = (SELECT value FROM OPENJSON(@json, '$') as x where x.[key] = @Counter))
    SET @Counter  = @Counter  + 1
END

GO

/******CREATE DELETE_VIEW******/

CREATE procedure [dbo].[workscreen_delete_view] @param int
as
 delete from ROLE_WOSC where  WOSC_ID =@param
 delete from WORKSCREENS where  id =@param

GO

/******CREATE DISABLE_VIEW******/
create procedure [dbo].[workscreen_disable_view] @param int
as
 update WORKSCREENS set ENV_DESKTOP = 'N' , env_mobile = 'N', ENV_WEB = 'N' where id =@param
GO


/******CREATE ENABLE_VIEW******/
create procedure [dbo].[workscreen_enable_view] @param int
as
 update WORKSCREENS set ENV_DESKTOP = 'Y' , env_mobile = 'Y', ENV_WEB = 'Y' where id =@param
GO



/******INSERTAR VISTA A LA TAULA******/
insert into [dbo].[WORKSCREENS] (text,MENU_STRUCTURE,SEQUENCE,CLASSNAME,ENV_DESKTOP,ENV_MOBILE,ENV_WEB) VALUES ('ImportWorkViews','Admin',0,'com.sibvisions.module.user.screens.ImportWorkViewsWorkScreen','Y','Y','Y')




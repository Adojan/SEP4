USE [PrimaryForProject]
GO
/****** Object:  Table [dbo].[Measurements]    Script Date: 5/15/2019 9:45:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Measurements](
	[CO2Percentage] [decimal](18, 0) NULL,
	[Temperature] [decimal](18, 0) NULL,
	[Humidity] [decimal](18, 0) NULL,
	[Room] [nchar](10) NOT NULL,
	[ID] [int] NOT NULL,
	[DateAndTime] [datetime] NULL,
 CONSTRAINT [PK_Measurements_1] PRIMARY KEY CLUSTERED 
(
	[Room] ASC,
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

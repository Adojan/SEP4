USE [ForProject]
GO
/****** Object:  Table [dbo].[AllCO2Facts]    Script Date: 5/15/2019 9:43:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AllCO2Facts](
	[RoomId] [int] NULL,
	[ID] [int] NOT NULL,
	[CO2Percentage] [decimal](15, 0) NULL,
	[Time] [time](7) NULL,
	[Date] [date] NULL,
 CONSTRAINT [PK_AllCO2Facts] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AllHumidityFacts]    Script Date: 5/15/2019 9:43:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AllHumidityFacts](
	[RoomId] [int] NULL,
	[ID] [int] NOT NULL,
	[HumidityPercentage] [decimal](15, 0) NULL,
	[Time] [time](7) NULL,
	[Date] [date] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AllTemperatureFacts]    Script Date: 5/15/2019 9:43:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AllTemperatureFacts](
	[RoomId] [int] NULL,
	[ID] [int] NOT NULL,
	[Temperature] [decimal](15, 0) NULL,
	[Time] [time](7) NULL,
	[Date] [date] NULL,
 CONSTRAINT [PK_AllTemperatureFacts] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Rooms]    Script Date: 5/15/2019 9:43:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Rooms](
	[RoomId] [int] NOT NULL,
	[Block] [nchar](50) NULL,
	[Building] [nchar](50) NULL,
	[Floor] [int] NULL,
 CONSTRAINT [PK_Rooms] PRIMARY KEY CLUSTERED 
(
	[RoomId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[STAGE.AllCO2Facts]    Script Date: 5/15/2019 9:43:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[STAGE.AllCO2Facts](
	[RoomId] [int] NULL,
	[ID] [int] NOT NULL,
	[CO2Percentage] [decimal](15, 0) NULL,
	[Time] [time](7) NULL,
	[Date] [date] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[STAGE.AllHumidityFacts]    Script Date: 5/15/2019 9:43:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[STAGE.AllHumidityFacts](
	[RoomId] [nchar](10) NULL,
	[ID2] [int] NOT NULL,
	[HumidityPercentage] [decimal](15, 0) NULL,
	[Time] [time](7) NULL,
	[Date] [date] NULL,
	[AverageHumidityPerMonth] [decimal](18, 0) NULL,
	[AverageHumidityPerYear] [decimal](18, 0) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[STAGE.AllTemperatureFacts]    Script Date: 5/15/2019 9:43:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[STAGE.AllTemperatureFacts](
	[RoomId] [int] NULL,
	[ID] [int] NOT NULL,
	[Temperature] [decimal](15, 0) NULL,
	[Time] [time](7) NULL,
	[Date] [date] NULL,
 CONSTRAINT [PK_STAGE.AllTeperatureFacts] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 5/15/2019 9:43:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[UserId] [int] NOT NULL,
	[FirstName] [nchar](50) NULL,
	[LastName] [nchar](50) NULL,
	[DOB] [datetime] NULL,
	[Phone] [int] NULL,
	[Email] [nchar](50) NULL,
	[Password] [nchar](50) NULL,
	[Major] [nchar](100) NULL,
	[Type] [varchar](50) NULL,
	[Street] [nchar](100) NULL,
	[StreetNr] [int] NULL,
	[City] [nchar](50) NULL,
	[PostalCode] [int] NULL,
	[Country] [nchar](50) NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[AllCO2Facts]  WITH CHECK ADD  CONSTRAINT [FK_AllCO2Facts_Rooms] FOREIGN KEY([RoomId])
REFERENCES [dbo].[Rooms] ([RoomId])
GO
ALTER TABLE [dbo].[AllCO2Facts] CHECK CONSTRAINT [FK_AllCO2Facts_Rooms]
GO
ALTER TABLE [dbo].[AllHumidityFacts]  WITH CHECK ADD  CONSTRAINT [FK_AllHumidityFacts_Rooms] FOREIGN KEY([RoomId])
REFERENCES [dbo].[Rooms] ([RoomId])
GO
ALTER TABLE [dbo].[AllHumidityFacts] CHECK CONSTRAINT [FK_AllHumidityFacts_Rooms]
GO
ALTER TABLE [dbo].[AllTemperatureFacts]  WITH CHECK ADD  CONSTRAINT [FK_AllTemperatureFacts_Rooms] FOREIGN KEY([RoomId])
REFERENCES [dbo].[Rooms] ([RoomId])
GO
ALTER TABLE [dbo].[AllTemperatureFacts] CHECK CONSTRAINT [FK_AllTemperatureFacts_Rooms]
GO


INSERT INTO [dbo].[sectors] ([name])VALUES('BONAO');
INSERT INTO [dbo].[sectors] ([name])VALUES('LA ROMANA');
INSERT INTO [dbo].[sectors] ([name])VALUES('SAMANA');
INSERT INTO [dbo].[sectors] ([name])VALUES('SANTO DOMINGO');


INSERT INTO [dbo].[user_type]([id],[type]) VALUES(1,'ADMINISTRADOR');
INSERT INTO [dbo].[user_type]([id],[type]) VALUES(2,'COORDINADOR');

INSERT INTO [dbo].[users]([cedula],[email],[name],[password],[user_type_id]) VALUES('11111111111','admin@gmail.com','ADMIN','123',1);



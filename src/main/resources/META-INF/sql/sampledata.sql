INSERT INTO USUARIO (CORREO, USUARIO, CONTRASEÑA, FECHACREACION,ROL) VALUES ('admin@red.ujaen.es', 'admin', 'password123', TO_DATE('10/01/2020', 'dd/MM/yyyy'),'ADMINISTRADORES');
INSERT INTO USUARIO (CORREO, USUARIO, CONTRASEÑA, FECHACREACION,ROL) VALUES ('usuario1@red.ujaen.es', 'usuario1', 'password123', TO_DATE('11/01/2020', 'dd/MM/yyyy'),'USUARIOS');
INSERT INTO USUARIO (CORREO, USUARIO, CONTRASEÑA, FECHACREACION,ROL) VALUES ('usuario2@red.ujaen.es', 'usuario2', 'password123', TO_DATE('12/02/2020', 'dd/MM/yyyy'),'USUARIOS');
INSERT INTO USUARIO (CORREO, USUARIO, CONTRASEÑA, FECHACREACION,ROL) VALUES ('usuario3@red.ujaen.es', 'usuario3', 'password123', TO_DATE('13/03/2020', 'dd/MM/yyyy'),'USUARIOS');
INSERT INTO USUARIO (CORREO, USUARIO, CONTRASEÑA, FECHACREACION,ROL) VALUES ('usuario4@red.ujaen.es', 'usuario4', 'password123', TO_DATE('14/04/2020', 'dd/MM/yyyy'),'USUARIOS');

INSERT INTO MEME (FECHASUBIDA, LIKES, RUTAIMG, TITULO, USUARIO_ID) VALUES (TO_DATE('11/01/2020', 'dd/MM/yyyy'), 0, 'test_meme.JPG', 'Memarrillo 1', 1);
INSERT INTO MEME (FECHASUBIDA, LIKES, RUTAIMG, TITULO, USUARIO_ID) VALUES (TO_DATE('12/02/2020', 'dd/MM/yyyy'), 0, 'test_meme.JPG', 'Memarrillo 2', 2);
INSERT INTO MEME (FECHASUBIDA, LIKES, RUTAIMG, TITULO, USUARIO_ID) VALUES (TO_DATE('13/03/2020', 'dd/MM/yyyy'), 0, 'test_meme.JPG', 'Memarrillo 3', 3);
INSERT INTO MEME (FECHASUBIDA, LIKES, RUTAIMG, TITULO, USUARIO_ID) VALUES (TO_DATE('14/04/2020', 'dd/MM/yyyy'), 0, 'test_meme.JPG', 'Memarrillo 4', 4);

INSERT INTO MEME (FECHASUBIDA, LIKES, RUTAIMG, TITULO, USUARIO_ID) VALUES (TO_DATE('14/04/2020', 'dd/MM/yyyy'), 0, 'test_meme.JPG', 'Memarrillo 5', 1);
INSERT INTO MEME (FECHASUBIDA, LIKES, RUTAIMG, TITULO, USUARIO_ID) VALUES (TO_DATE('14/04/2020', 'dd/MM/yyyy'), 0, 'test_meme.JPG', 'Memarrillo 6', 1);
INSERT INTO MEME (FECHASUBIDA, LIKES, RUTAIMG, TITULO, USUARIO_ID) VALUES (TO_DATE('14/04/2020', 'dd/MM/yyyy'), 0, 'test_meme.JPG', 'Memarrillo 7', 1);
INSERT INTO MEME (FECHASUBIDA, LIKES, RUTAIMG, TITULO, USUARIO_ID) VALUES (TO_DATE('14/04/2020', 'dd/MM/yyyy'), 0, 'test_meme.JPG', 'Memarrillo 8', 1);

INSERT INTO COMENTARIO (MEME_ID, USUARIO_ID, TEXTO, FECHASUBIDA) VALUES (1, 1, 'De memarreo va la cosa', TO_DATE('21/01/2020', 'dd/MM/yyyy'));
INSERT INTO COMENTARIO (MEME_ID, USUARIO_ID, TEXTO, FECHASUBIDA) VALUES (1, 2, 'Memarreo que te veo', TO_DATE('21/01/2020', 'dd/MM/yyyy'));
INSERT INTO COMENTARIO (MEME_ID, USUARIO_ID, TEXTO, FECHASUBIDA) VALUES (1, 3, 'Memarreo, el empalador', TO_DATE('21/01/2020', 'dd/MM/yyyy'));
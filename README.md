![Logo MEMUJA](https://github.com/avi-ss/MEMUJA/blob/master/src/main/webapp/resources/images/layout/test_logo_white.png)

# DESCRIPCION
MEMUJA es una página de humor y memes de la UJA. La idea principal es servir de plataforma para que los estudiantes de todas las carreras de la UJA puedan compartir memes y chistes sobre sus carreras, afianzando los vínculos con sus compañeros y sirviendo de punto de encuentro para relajarse un rato, compartir experiencias y conocer a otra gente. No sólo se pretende incluir a estudiantes, sino a toda persona que trabaje en la UJA.

Estos memes siempre serán respetuosos y no faltarán el respeto ni discriminarán a nadie de ninguna forma. Existirá una moderación de todos los memes que se suban a la web, siendo que los moderadores deben aceptar cada meme antes de su publicación. Si un meme no sigue las reglas no será aceptado.

### CHANGELOG
####  Iteración 5
- Creado servicio REST y comunicación entre PhaserJS y el REST (Alberto)
- Validación en cliente (Alberto y Antonio)
- Implementación del juego en la aplicación (Javier)

###### Novedades
- Integración de juego básico PhaserJS 
- Validación en cliente del formulario de registro


---

##### Iteración 4

- Fix en la forma de añadir comentarios (Alberto)
- Se han movido algunas funcionalidades del Usuario del Ctrl a DAO (Alberto)
- Indicación visual de quién subió los memes (Javier)
- Modificación de la gestión de la entidad Usuario con un ID autoincremental (Alberto, Antonio)
- Configuración e implementación final de la autentificación (Antonio)
- Muestra del nº de comentarios que tiene un meme (Javier)
- Añadido Rol al usuario (admin o user normal) (Alberto, Antonio)
- Implementación de Borrado de Usuarios (Javier)
- Modificación de la gestión de la entidad Meme con un ID autoincremental (Alberto, Antonio)
- Implementación de Visualización de Memes en el perfil de los usuarios; Acceso a la publicación desde el perfil (Javier)
- Se han movido algunas funcionalidades del Meme del Ctrl a DAO (Alberto)
- Implementación de gestión del admin, ahora puede eliminar/editar usuarios, comentarios y memes. (Antonio)
- Modificación de la gestión de la entidad Comentario con un ID autoincremental (Alberto, Antonio)
- Formulario de gestión de memes para el Admin (Antonio)
- Implementación de subida de nuevos memes por parte del usuario (Javier)
- Ahora el usuario muestra la información en su perfil (nº publicaciones, likes y comentarios publicados)



###### Novedades

- Ahora puedes registrarte y tener tu propio perfil.
- En el perfil de cada usuario se muestran los memes que ha subido.
- Cada usuario puede añadir comentarios a los distintos memes, tantos comentarios como desee (antes sólo se podía añadir un comentario por usuario en cada meme).
- Cada usuario puede subir memes a la página (no puede subir imágenes al servidor, cada meme se creará con la imagen por defecto, pero con los demás datos correctos).
- El usuario Admin puede gestionar las entidades eliminando usuarios, comentarios y publicaciones. Además, puede modificar a los usuarios.  
- Ahora se muestra el número de comentarios que tiene un meme tanto en la previsualización como en la publicación. 



---

##### Iteración 3

- Todas las entidades utilizan JPA para persistencia y guardado de datos (Comentarios, Usuarios y Memes)
- Implementación de Log-In. Hay páginas no autorizadas para usuarios que no estén registrados y logeados.
- Establecidas relaciones entre entidades para que los datos se guarden correctamente (por ejemplo, relación entre Meme-Usuario o Comentario-Meme)
- Capacidad de añadir comentarios a memes siendo un usuario determinado. 
- Autentificación básica de usuarios.



###### Novedades

- Ahora te puedes logear como usuario o admin (admin/secret1 - user/secret2)
- Ahora cada meme adjunta sus comentarios, siendo diferentes para cada meme.
- Ahora puedes añadir comentarios a los distintos memes (por ahora sólo como "usuario4", hasta que solucionemos un problema con los usuarios y JPA en la siguiente iteración)
- En general no se ha añadido mucha más funcionalidad, pero se ha reestructurado el programa para funcionar con JPA y tener persistencia, lo que se terminará de desarrollar en la siguiente iteración.



---

##### Iteración 2

- Entidad Meme: Controlador, DAO y Bean. (Javier)
- Expandir y arreglar Componente Meme (Javier)
- Entidad Usuario: Controlador, DAO y Bean (Alberto)
- Entidad Comentario: DAO y Bean (Alberto)
- Entidad Comentario: Controlador (Antonio)
- Añadir controladores a las vistas. Se creó una vista aparte para probar las funciones CRUD (Antonio)
- Validación y mensajes de error de los controladores (Antonio)



###### Novedades

- Ahora puedes hacer un registro básico de usuario para añadir nuevos usuarios.

- Ahora cada meme es visualizado individualmente con su propia página, aunque aún los comentarios no son individuales.

- Ahora cada usuario se puede visualizar individualmente, con su propia página. Aunque aún no están asociados los memes de cada usuario.

- Se ha reprogramado la parte de mejores usuarios y mejor meme de la semana. 

- Existe una vista "admin" desde la que poder eliminar, editar y ver los usuarios existentes (se accede desde Registrar)

- Se ha creado la base desde la que, una vez implementado JPA, se puedan relacionar las entidades para visualizar correctamente toda la página y la información de cada entidad. 

  

------

##### Iteración 1

- Añadidas todas las vista principales de la aplicación. Ahora el usuario puede navegar por todas ellas fácilmente. Las vistas incluidas son:

  - Vista página principal (Javier)

  - Vista para ver una publicación (Javier)

  - Vista para subir un meme (Alberto)

  - Vista de un perfil de usuario (Alberto)

  - Vista de la página de registro (Antonio)

  - Vista de la página de normas (Antonio)

  - Vista de la página con información de contacto (Antonio)

  - Vista de la página con información sobre nosotros (Antonio)

    

- Ajustado el CSS básico para añadir algunas personalizaciones, como el fondo del header o el background principal. (Alberto)

- Añadido un layout que sirve de base para todas las demás vistas. Todas las vistas derivan de este layout. (Alberto)

- Creado un componente Meme, que encapsula un meme y su información. Esta es una versión básica que será mejorada en la siguiente iteración. El componente se utiliza ahora mismo en la página principal. (Javier)

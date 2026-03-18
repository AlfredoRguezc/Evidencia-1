# Calculadora de IMC - Documentación Técnica

## 1. Descripción General 
La aplicación es un sistema web interactivo diseñado para calcular el Índice de Masa Corporal (IMC) de un usuario basándose en sus datos físicos (edad, sexo, estatura y peso). El sistema proporciona tanto una Interfaz Gráfica de Usuario (GUI) a través de páginas web como una API RESTful para la integración con otros clientes o servicios.

## 2. Arquitectura del Sistema
El proyecto está construido bajo el patrón arquitectónico Modelo-Vista-Controlador (MVC), utilizando Spring Framework como motor principal para el enrutamiento y la inyección de dependencias. 

La arquitectura se divide en las siguientes capas lógicas:
*  **Capa de Presentación (Vista):** Archivos JSP (`index.jsp`, `resultado.jsp`) que renderizan la interfaz de usuario.
*  **Capa de Control (Controlador):** Gestiona las peticiones HTTP entrantes, delegando la lógica web tradicional a `IMCController` y las peticiones de datos crudos a `IMCRestController`.
*  **Capa de Negocio (Servicio y Modelo):** Contiene la lógica matemática pura en `CalculadoralMC`, la estructura de datos en la clase `Usuario`, y la persistencia temporal de datos a través de `IMCServicio`.
*  **Capa de Intercepción (Filtros):** Actúa como un middleware de seguridad y validación antes de que las peticiones alcancen los controladores.

##  3. Stack Tecnológico:
*  **Lenguaje:** Java 
*  **Framework Principal:** Spring MVC (Web y REST) 
*  **Servidor de Aplicaciones:** Eclipse GlassFish 7.0.3 (Jakarta EE 10) 
*  **Motor de Vistas:** JSP (JavaServer Pages) 
*  **Gestión de Dependencias y Estructura:** Proyecto Web estándar de Java EE.

##  4. Estructura de Paquetes 
*  **`modelo`**: Contiene las entidades principales.  `Usuario.java` define los atributos del usuario y `CalculadoralMC.java` encapsula las reglas de negocio para el cálculo numérico y la obtención del diagnóstico de salud.
*  **`controlador`**: Punto de entrada de las peticiones. 
    *  `IMCController.java`: Maneja las peticiones web (`GET /` y `POST /calcular`) devolviendo vistas JSP.
    *  `IMCRestController.java`: Expone la API de consumo mediante JSON
*  **`servicio`**: Contiene `IMCServicio.java`, que actúa como intermediario para aislar la lógica de almacenamiento y cálculo del controlador REST.
*  **`filtro`**: Contiene `IMCFiltro.java`, encargado de interceptar y sanear las peticiones.

##  5. Flujo de Funcionamiento (Aplicación Web)
1.  **Petición:** El usuario ingresa a la ruta raíz `/`.  Spring MVC intercepta la petición y el `IMCController` devuelve la vista `index.jsp`.
2.  **Envío de Formulario:** El usuario llena los datos y envía un método `POST` hacia `/calcular`.
3.  **Validación (Filtro):** La petición es interceptada por el `IMCFiltro`.  Si los datos son inválidos (ej. vacíos o caracteres no numéricos) o están fuera de rango (ej. estatura mayor a 3.0m), el filtro detiene la petición y devuelve un Error HTTP 400 (Bad Request).
4.  **Procesamiento:** Si los datos son válidos, el `IMCController` recibe los parámetros, instancia un objeto `Usuario`, y llama al modelo `CalculadoralMC`.
5.  **Respuesta:** El controlador empaqueta el objeto usuario, el valor numérico del IMC y el diagnóstico en texto, y los envía a la vista `resultado.jsp` para ser mostrados al usuario.

##  6. Documentación de la API RESTful
 El sistema expone un punto de enlace base en `/api/imc` para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar).  Las respuestas se entregan en formato JSON.

| Método HTTP | Endpoint | Descripción | Respuestas HTTP |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/imc` |  Retorna la lista de todos los registros de usuarios guardados.  |  `200 OK`  |
| **GET** | `/api/imc/{indice}` |  Retorna un registro específico, su IMC y diagnóstico basado en su posición.  |  `200 OK`, `404 Not Found`  |
| **POST** | `/api/imc` | Crea un nuevo registro.  Requiere un JSON con nombre, edad, sexo, estatura y peso.  |  `201 Created`, `400 Bad Request`  |
| **PUT** | `/api/imc/{indice}` |  Actualiza un registro existente mediante su índice enviando un JSON nuevo.  |  `200 OK`, `404 Not Found`  |
| **DELETE** | `/api/imc/{indice}` |  Elimina permanentemente el registro asociado al índice proporcionado.  |  `200 OK`, `404 Not Found`  |

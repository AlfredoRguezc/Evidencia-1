# [cite_start]Calculadora de IMC - Documentación Técnica [cite: 17]

## [cite_start]1. Descripción General [cite: 18]
[cite_start]La aplicación es un sistema web interactivo diseñado para calcular el Índice de Masa Corporal (IMC) de un usuario basándose en sus datos físicos (edad, sexo, estatura y peso)[cite: 19]. [cite_start]El sistema proporciona tanto una Interfaz Gráfica de Usuario (GUI) a través de páginas web como una API RESTful para la integración con otros clientes o servicios[cite: 20].

## [cite_start]2. Arquitectura del Sistema [cite: 21]
[cite_start]El proyecto está construido bajo el patrón arquitectónico Modelo-Vista-Controlador (MVC), utilizando Spring Framework como motor principal para el enrutamiento y la inyección de dependencias[cite: 22]. 

[cite_start]La arquitectura se divide en las siguientes capas lógicas[cite: 23]:
* [cite_start]**Capa de Presentación (Vista):** Archivos JSP (`index.jsp`, `resultado.jsp`) que renderizan la interfaz de usuario[cite: 24].
* [cite_start]**Capa de Control (Controlador):** Gestiona las peticiones HTTP entrantes, delegando la lógica web tradicional a `IMCController` y las peticiones de datos crudos a `IMCRestController`[cite: 25].
* [cite_start]**Capa de Negocio (Servicio y Modelo):** Contiene la lógica matemática pura en `CalculadoralMC`, la estructura de datos en la clase `Usuario`, y la persistencia temporal de datos a través de `IMCServicio`[cite: 26].
* [cite_start]**Capa de Intercepción (Filtros):** Actúa como un middleware de seguridad y validación antes de que las peticiones alcancen los controladores[cite: 27].

## [cite_start]3. Stack Tecnológico [cite: 28]
* [cite_start]**Lenguaje:** Java [cite: 29]
* [cite_start]**Framework Principal:** Spring MVC (Web y REST) [cite: 30]
* [cite_start]**Servidor de Aplicaciones:** Eclipse GlassFish 7.0.3 (Jakarta EE 10) [cite: 31]
* [cite_start]**Motor de Vistas:** JSP (JavaServer Pages) [cite: 32]
* [cite_start]**Gestión de Dependencias y Estructura:** Proyecto Web estándar de Java EE[cite: 33].

## [cite_start]4. Estructura de Paquetes [cite: 34]
* [cite_start]**`modelo`**: Contiene las entidades principales[cite: 36]. [cite_start]`Usuario.java` define los atributos del usuario y `CalculadoralMC.java` encapsula las reglas de negocio para el cálculo numérico y la obtención del diagnóstico de salud[cite: 36].
* [cite_start]**`controlador`**: Punto de entrada de las peticiones[cite: 37]. 
    * [cite_start]`IMCController.java`: Maneja las peticiones web (`GET /` y `POST /calcular`) devolviendo vistas JSP[cite: 38].
    * [cite_start]`IMCRestController.java`: Expone la API de consumo mediante JSON[cite: 39].
* [cite_start]**`servicio`**: Contiene `IMCServicio.java`, que actúa como intermediario para aislar la lógica de almacenamiento y cálculo del controlador REST[cite: 40].
* [cite_start]**`filtro`**: Contiene `IMCFiltro.java`, encargado de interceptar y sanear las peticiones[cite: 41].

## [cite_start]5. Flujo de Funcionamiento (Aplicación Web) [cite: 42]
1. [cite_start]**Petición:** El usuario ingresa a la ruta raíz `/`[cite: 43]. [cite_start]Spring MVC intercepta la petición y el `IMCController` devuelve la vista `index.jsp`[cite: 44].
2. [cite_start]**Envío de Formulario:** El usuario llena los datos y envía un método `POST` hacia `/calcular`[cite: 45].
3. [cite_start]**Validación (Filtro):** La petición es interceptada por el `IMCFiltro`[cite: 46]. [cite_start]Si los datos son inválidos (ej. vacíos o caracteres no numéricos) o están fuera de rango (ej. estatura mayor a 3.0m), el filtro detiene la petición y devuelve un Error HTTP 400 (Bad Request)[cite: 47].
4. [cite_start]**Procesamiento:** Si los datos son válidos, el `IMCController` recibe los parámetros, instancia un objeto `Usuario`, y llama al modelo `CalculadoralMC`[cite: 48].
5. [cite_start]**Respuesta:** El controlador empaqueta el objeto usuario, el valor numérico del IMC y el diagnóstico en texto, y los envía a la vista `resultado.jsp` para ser mostrados al usuario[cite: 49].

## [cite_start]6. Documentación de la API RESTful [cite: 50]
[cite_start]El sistema expone un punto de enlace base en `/api/imc` para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)[cite: 51]. [cite_start]Las respuestas se entregan en formato JSON[cite: 52].

| Método HTTP | Endpoint | Descripción | Respuestas HTTP |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/imc` | [cite_start]Retorna la lista de todos los registros de usuarios guardados. [cite: 53] | [cite_start]`200 OK` [cite: 53] |
| **GET** | `/api/imc/{indice}` | [cite_start]Retorna un registro específico, su IMC y diagnóstico basado en su posición. [cite: 53] | [cite_start]`200 OK`, `404 Not Found` [cite: 53] |
| **POST** | `/api/imc` | Crea un nuevo registro. [cite_start]Requiere un JSON con nombre, edad, sexo, estatura y peso. [cite: 53] | [cite_start]`201 Created`, `400 Bad Request` [cite: 53] |
| **PUT** | `/api/imc/{indice}` | [cite_start]Actualiza un registro existente mediante su índice enviando un JSON nuevo. [cite: 53] | [cite_start]`200 OK`, `404 Not Found` [cite: 53] |
| **DELETE** | `/api/imc/{indice}` | [cite_start]Elimina permanentemente el registro asociado al índice proporcionado. [cite: 53] | [cite_start]`200 OK`, `404 Not Found` [cite: 53] |

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calculadora IMC</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 500px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        h1 {
            color: #333;
            text-align: center;
        }
        form {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
            color: #555;
        }
        input, select {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            width: 100%;
            padding: 12px;
            margin-top: 25px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover { background-color: #45a049; }
        .error {
            color: red;
            text-align: center;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <h1>Calculadora de IMC</h1>

    <% if (request.getAttribute("error") != null) { %>
        <p class="error">${error}</p>
    <% } %>

    <form action="calcular.do" method="post">
        <label>Nombre:</label>
        <input type="text" name="nombre" required />

        <label>Edad:</label>
        <input type="number" name="edad" required min="1" />

        <label>Sexo:</label>
        <select name="sexo">
            <option value="Masculino">Masculino</option>
            <option value="Femenino">Femenino</option>
        </select>

        <label>Estatura (en metros, ej: 1.75):</label>
        <input type="number" name="estatura" step="0.01" required min="0.5" max="3.0" />

        <label>Peso (en kg):</label>
        <input type="number" name="peso" step="0.1" required min="1" />

        <button type="submit">Calcular IMC</button>
    </form>
</body>
</html>
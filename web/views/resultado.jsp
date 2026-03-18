<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Resultado IMC</title>
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
        .resultado {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .dato {
            margin: 10px 0;
            padding: 10px;
            border-bottom: 1px solid #eee;
        }
        .dato span {
            font-weight: bold;
            color: #555;
        }
        .imc-valor {
            font-size: 2em;
            text-align: center;
            color: #4CAF50;
            margin: 20px 0;
        }
        .diagnostico {
            text-align: center;
            font-size: 1.3em;
            padding: 10px;
            border-radius: 4px;
            margin: 10px 0;
            background-color: #e8f5e9;
            color: #2e7d32;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 25px;
            padding: 12px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            font-size: 16px;
        }
        a:hover { background-color: #45a049; }
    </style>
</head>
<body>
    <h1>Resultado IMC</h1>

    <div class="resultado">
        <div class="dato"><span>Nombre:</span> ${usuario.nombre}</div>
        <div class="dato"><span>Edad:</span> ${usuario.edad} años</div>
        <div class="dato"><span>Sexo:</span> ${usuario.sexo}</div>
        <div class="dato"><span>Estatura:</span> ${usuario.estatura} m</div>
        <div class="dato"><span>Peso:</span> ${usuario.peso} kg</div>

        <div class="imc-valor">
            IMC: <strong><%= String.format("%.2f", (Double) request.getAttribute("imc")) %></strong>
        </div>

        <div class="diagnostico">
            Diagnóstico: <strong>${diagnostico}</strong>
        </div>

        <a href="/">← Calcular otro IMC</a>
    </div>
</body>
</html>
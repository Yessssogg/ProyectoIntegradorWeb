<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultorio Clinico</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
    <img class="wave" src="../img/wave.png" alt="">
    <div class="contenedor">
        <div class="img">
            <img src="../img/bg.svg" alt="">
        </div>
        <div class="contenido-login">
            <form action="${pageContext.request.contextPath}/SvLogin" method="POST">
                <img src="../img/logo.png" alt="">
                <h2>Clinica</h2>
                <div class="input-div dni">
                    <div class="i">
                        <i class="fas fa-user"></i>
                    </div>
                    <div class="div">
                        <h5>USUARIO</h5>
                        <input type="text" id="usuario" name="usuario" class="input" >
                    </div>
                </div>
                <div class="input-div pass">
                    <div class="i">
                        <i class="fas fa-lock"></i>
                    </div>
                    <div class="div">
                        <h5>Contraseña</h5>
                        <input type="password" id="contrasenia" name="contrasenia" class="input" >
                    </div>
                </div>
                <a href="">Forgot your password?</a>
                <input type="submit" class="btn" value="Iniciar Sesión">
            </form>
        </div>
    </div>
</body>
<script src="../js/login.js"></script>
</html>

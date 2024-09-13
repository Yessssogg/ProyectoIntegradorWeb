

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body id="page-top">
    <style>
        .imgLogo{
            width:70px;
        }
        .floating-window {
            display: none; /* Hidden by default */
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 90%;
            background-color: #fff;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            z-index: 1000;
        }

        .floating-window-content {
            padding: 20px;
            text-align: left;
        }

        .close-btn {
            float: right;
            font-size: 24px;
            font-weight: bold;
            cursor: pointer;
        }

        .close-btn:hover {
            color: red;
        }

        .section-title {
            font-size: 18px;
            margin-top: 20px;
            font-weight: bold;
            text-transform: uppercase;
            padding: 10px;
            border-radius: 5px;
        }

        /* Estilos específicos para cada sección */
        .mision {
            background-color: #ffcccc;
            max-width: max-content;
            text-align: center;
        }

        .vision {
            background-color: #ccffcc;
            max-width: max-content;
            text-align: center;
        }

        .nosotros {
            background-color: #cce5ff;
            max-width: max-content;
            text-align: center;
        }

        .contacto {
            background-color: #ffe6b3;
            max-width: max-content;
            text-align: center;
        }

        .ubicacion {
            background-color: #e6ccff;
            max-width: max-content;
            text-align: center;
        }
    </style>
    <!-- Page Wrapper -->
    <div id="wrapper">
        <!-- Ventana flotante --> 
        <div id="floatingWindow" class="floating-window">
            <div class="floating-window-content">
                <span class="close-btn" id="closeBtn">&times;</span>
                <h2 style="text-align: center">Bienvenido al Sistema</h2>

                <h3 class="section-title mision">Misión:</h3>
                <p>Brindar atención médica accesible y de calidad, ofreciendo servicios especializados y de urgencias para garantizar la salud y bienestar de nuestros pacientes...</p>

                <h3 class="section-title vision">Visión:</h3>
                <p>Ser la clínica líder en atención médica integral en Lima Norte, reconocida por su excelencia, accesibilidad y tecnología de punta, contribuyendo al bienestar de la comunidad y al desarrollo del sector salud.</p>

                <h3 class="section-title nosotros">Nosotros:</h3>
                <p>La Clínica San Juan, fundada en 2004, ha trabajado constantemente en mejorar la calidad de sus servicios para brindar una atención médica oportuna y eficiente. Con un equipo de profesionales altamente capacitados y un enfoque en la satisfacción del paciente, seguimos comprometidos con la salud de nuestra comunidad.</p>

                <h3 class="section-title contacto">Contacto:</h3>
                <p>Teléfono: (01) 234-5678</p>
                <p>Correo Electrónico: contacto@clinicasanjuan.pe</p>
                <p>WhatsApp: +51 987 654 321</p>

                <h3 class="section-title ubicacion">Ubicación de la Empresa - Sedes:</h3>
                <p>Sede Principal: Av. Alfredo Mendiola 950, San Martín de Porres, Lima</p>
                <p>Sede Surco: Av. Primavera 150, Santiago de Surco, Lima</p>
                <p>Sede Callao: Av. Colonial 5000, Callao</p>
            </div>
        </div>

                <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="#" id="logoLink">
                <div class="sidebar-brand-icon">                   
                    <img class="imgLogo" src="./img/health.jpg" alt="alt"/>
                </div>
                <div class="sidebar-brand-text mx-3">Consultorio Clinico</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="index.html">
                    <i class="fas fa-solid fa-bars"></i>
                    <span>Menu</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Gestion
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas far fa-user-md"></i>
                    <span>Doctores</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Acciones</h6>
                        <a class="collapse-item" href="SvDoctor">Ver Doctores</a>
                        <a class="collapse-item" href="altaDoctor.jsp">Alta Doctores</a>                       
                    </div>
                </div>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-solid fa-hospital-user"></i>
                    <span>Pacientes</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Acciones</h6>
                        <a class="collapse-item" href="">Ver Pacintes</a>
                        <a class="collapse-item" href="">Alta Pacientes</a>                      
                    </div>
                </div>
            </li>
            
            
            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUsers"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-solid fa-hospital-user"></i>
                    <span>Usuarios</span>
                </a>
                <div id="collapseUsers" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Acciones</h6>
                        <a class="collapse-item" href="SvUsuarios">Ver Usuarios</a>
                        <a class="collapse-item" href="altaUsuarios.jsp">Alta Usuarios</a>                      
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            
         
        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>
                                      
                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                      
                            

                        
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas McGee</span>
                                <img class="img-profile rounded-circle"
                                    src="img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Activity Log
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Clinica San Juan</h1>
                        
                    </div>
                </div>
            </div>
            
        </div>
    </div>
<script>
    // Abrir la ventana flotante
    document.getElementById("logoLink").addEventListener("click", function() {
        document.getElementById("floatingWindow").style.display = "block";
    });

    // Cerrar la ventana flotante
    document.getElementById("closeBtn").addEventListener("click", function() {
        document.getElementById("floatingWindow").style.display = "none";
    });

    // Cerrar la ventana al hacer clic fuera de ella
    window.addEventListener("click", function(event) {
        const floatingWindow = document.getElementById("floatingWindow");
        if (event.target == floatingWindow) {
            floatingWindow.style.display = "none";
        }
    });

    </script>
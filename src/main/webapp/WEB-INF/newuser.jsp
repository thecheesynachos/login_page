<html>
<head>
    <title>Add New User</title>
    <link rel="stylesheet" href="../bootstrap/css/sb-admin-2.css">
</head>
<body class="bg-gradient-dark">

<div class="container fluid">
    
    <!-- Topbar -->
    <nav class="navbar navbar-expand navbar-dark bg-light topbar mb-4 static-top shadow">
    
        <img src="../res/logo2.png" height="70%", class="justify-content-center">
        
        <a class="nav-link" href="/">Home</a>
        
        <a class="nav-link" href="/newuser">Create a new user</a>
        
        <a class="nav-link" href="/users">See users</a>
        
        <!-- Topbar Navbar -->
        <ul class="navbar-nav ml-auto">
            
            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
            <li class="nav-item dropdown no-arrow d-sm-none">
                <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-search fa-fw"></i>
                </a>
                <!-- Dropdown - Messages -->
                <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                    <form class="form-inline mr-auto w-100 navbar-search">
                        <div class="input-group">
                            <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="button">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </li>
            
            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%=request.getSession().getAttribute("name")%></span>
                </a>
                <!-- Dropdown - User Information -->
                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                    <a class="dropdown-item" href="/logout">
                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                        Logout
                    </a>
                </div>
            </li>
        
        </ul>
    
    </nav>
    <!-- End of Topbar -->
    
    
    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Create a new user</h1>
                        </div>
                        <form class="user" action="/newuser" method="post">
                            <div class="form-group">
                                <input type="text" class="form-control form-control-user" id="username" name="username" placeholder="Username">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control form-control-user" id="password" name="password" placeholder="Password">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control form-control-user" id="name" name="name" placeholder="Name">
                            </div>
                            <div>
                                <input class="btn btn-primary btn-user btn-block" type="submit" value="Submit">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="../bootstrap/js/sb-admin-2.js"></script>

</div>


<!-- Bootstrap core JavaScript-->
<script src="../bootstrap/vendor/jquery/jquery.min.js"></script>
<script src="../bootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="../bootstrap/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="../bootstrap/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="../bootstrap/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="../bootstrap/vendor/datatables/dataTables.bootstrap4.min.js"></script>

</body>
</html>
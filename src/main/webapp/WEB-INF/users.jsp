<%@ page import="io.muic.ooc.webapp.MySQLJava" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Home page</title>
    <link rel="stylesheet" href="../bootstrap/css/sb-admin-2.css">
</head>


<body class="bg-gradient-dark">

<div class="container-fluid">
    
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

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">List of Users</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Name</th>
                        <th>Remove user</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        ResultSet rs = MySQLJava.getInstance().getUserData();
                        while(rs.next()){
                            try{
                    %>
                    <tr>
                        <td><%= rs.getString("id")%></td>
                        <td><%= rs.getString("username")%></td>
                        <td><%= rs.getString("name")%></td>
                        <td><a href="/removeuser"></a></td>
                    </tr>
                    <%
                            } catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
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

<!-- Page level custom scripts -->
<script src="../bootstrap/js/demo/datatables-demo.js"></script>

</body>

</html>
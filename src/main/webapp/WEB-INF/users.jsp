<%@ page import="io.muic.ooc.webapp.MySQLJava" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Users</title>
    <link rel="stylesheet" href="../bootstrap/css/sb-admin-2.css">
</head>


<body class="bg-gradient-dark">

<div class="container-fluid">
    
    <!-- Topbar -->
    <nav class="navbar navbar-expand navbar-dark bg-light topbar mb-4 static-top shadow">
    
        <img src="../res/logo2.png" height="70%", class="justify-content-center">
    
        <a class="nav-link" href="/users">Users list</a>
    
        <a class="nav-link" href="/newuser">Create a new user</a>

        
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
                    <span class="mr-2 d-none d-lg-inline text-gray-600"><%=MySQLJava.getInstance().getName((Integer)request.getSession().getAttribute("sessionId"))%></span>
                </a>
                <!-- Dropdown - User Information -->
                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                    <div class="dropdown-item">
                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                        Username: <%=MySQLJava.getInstance().getUsername((Integer)request.getSession().getAttribute("sessionId"))%>
                    </div>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/logout">
                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                        Logout
                    </a>
                </div>
            </li>
        
        </ul>
    
    </nav>
    <!-- End of Topbar -->
    
    <%
        if (request.getAttribute("message") != null){
    %>
    <div class="card shadow mb-4">
        <div class="card bg-<%=request.getAttribute("messagestatus") %> text-white shadow">
            <div class="card-body">
                <%=request.getAttribute("message") %>
            </div>
        </div>
    </div>
    <%
        }
    %>

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
                        <th>Edit user</th>
                        <th>Remove user</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        ResultSet rs = MySQLJava.getInstance().getUserData();
                        while(rs.next()){
                            try{
                            	String usernameEntry = rs.getString("username");
                            	String nameEntry = rs.getString("name");
                            	int idEntry = rs.getInt("id");
                    %>
                    <tr>
                        <td><%= idEntry %></td>
                        <td><%= usernameEntry %></td>
                        <td><%= nameEntry %></td>
                        <td><!-- Button trigger modal -->
                            <a href="#" data-toggle="modal" data-target="#edit<%= idEntry%>">Edit user</a>
    
                            <!-- Modal -->
                            <div class="modal fade" id="edit<%= idEntry %>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="editHeader">Edit <%= usernameEntry %> data</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <form action="/edituser" method="post">
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <label for="username-edit" class="col-form-label"> New username:</label>
                                                    <input type="text" class="form-control" id="username-edit" name="username-edit" value="<%= usernameEntry%>" >
                                                </div>
                                                <div class="form-group">
                                                    <label for="password-edit" class="col-form-label"> New password:</label>
                                                    <input type="password" class="form-control" id="password-edit" name="password-edit" >
                                                </div>
                                                <div class="form-group">
                                                    <label for="name-edit" class="col-form-label">New name:</label>
                                                    <input type="text" class="form-control" id="name-edit" name="name-edit" value="<%= nameEntry%>" >
                                                </div>
                                            </div>
                                            <input type="hidden" id="id-edit" name="id-edit" value=<%= idEntry%> >
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                <button type="submit" class="btn btn-primary">Edit user</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div></td>
                        <td>
                            <% if(idEntry != (Integer)request.getSession().getAttribute("sessionId") ){ %>
                            <!-- Button trigger modal -->
                            <a href="#" data-toggle="modal" data-target="#remove<%= idEntry%>">Remove user</a>
    
                            <!-- Modal -->
                            <div class="modal fade" id="remove<%= idEntry%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Confirmation</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            You are about to remove the user "<%= nameEntry%>". Are you sure?
                                        </div>
                                        <div class="modal-footer">
                                            <form action="/removeuser" method="post">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                                <button type="submit" class="btn btn-primary">Yes</button>
                                                <input type="hidden" id="id-to-remove" name="id-to-remove" value=<%= idEntry%>>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <% } %>
                        </td>
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
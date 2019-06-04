<html>

<head>
    <title>Login</title>
    <link rel="stylesheet" href="../bootstrap/css/sb-admin-2.css">
</head>

<body class="bg-gradient-dark">

<%
    if (request.getAttribute("message") != null){
%>

<div class="container fluid">

<div class="card shadow mb-4">
    <div class="card bg-<%=request.getAttribute("messagestatus") %> text-white shadow">
        <div class="card-body">
            <%=request.getAttribute("message") %>
        </div>
    </div>
</div>
</div>
<%
    }
%>

<div class="container">
    
    <!-- Outer Row -->
    <div class="row justify-content-center">
        
        <div class="col-xl-10 col-lg-12 col-md-9">
            
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <form action="/login" method="post">
                                    <img src="../res/logo2.png" width="100%", class="justify-content-center">
                                    
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Login</h1>
                                    </div>
                                    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
                                    <label for="username" class="sr-only">Username</label>
                                    <input type="text" name="username" id="username" class="form-control" placeholder="Username" required autofocus>
                                    <label for="inputPassword" class="sr-only">Password</label>
                                    <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
                                    <button class="btn btn-lg btn-dark btn-block" type="submit">Sign in</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        
        </div>
    
    </div>

</div>

        <script src="../bootstrap/js/sb-admin-2.js"></script>

    </body>
</html>

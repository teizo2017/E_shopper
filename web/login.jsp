<%
    if(request.getAttribute("statusMessage") == null){
        request.getRequestDispatcher("/login").forward(request, response);
    }
%>
<jsp:include page="/header.jsp" />
	
		<div class="header-bottom"><!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-9">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
						</div>
						<div class="mainmenu pull-left">
							<ul class="nav navbar-nav collapse navbar-collapse">
								<li><a href="index.jsp">Home</a></li>
								<li class="dropdown"><a href="#">Shop<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="shop.jsp">Products</a></li>
										<li><a href="checkout.jsp">Checkout</a></li> 
										<li><a href="cart.jsp">Cart</a></li> 
                                    </ul>
                                </li> 
								
								<li><a href="contact-us.jsp">Contact</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="search_box pull-right">
							<input type="text" placeholder="Search"/>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-bottom-->
	</header><!--/header-->
	
	<section id="form"><!--form-->
		<div class="container">
                        <%
                                String statusMessage = (String)request.getAttribute("statusMessage");
				if(statusMessage != null && !statusMessage.equals(""))
				{
			%>
                    <div class="step-one">
				<h2 class="heading"><%= statusMessage %></h2>
			</div>
                        <%
				}
			%>
			<div class="row">
				<div class="col-sm-4 col-sm-offset-1">
					<div class="login-form"><!--login form-->
						<h2>Login to your account</h2>
                                                <form method="post" action="login<% if(request.getParameter("checkout") != null)out.println("?checkout");%>">
							<input type="text" name="txtEmail" required placeholder="Email Address" />
							<input type="password" name="txtPassword" required placeholder="Password" />
							<button type="submit" name="btnLogin" class="btn btn-default">Login</button>
						</form>
					</div><!--/login form-->
				</div>
				<div class="col-sm-1">
					<h2 class="or">OR</h2>
				</div>
				<div class="col-sm-4">
					<div class="signup-form"><!--sign up form-->
						<h2>New User Signup!</h2>
						<form action="login<% if(request.getParameter("checkout") != null)out.println("?checkout");%>" method="post">
							<input type="text" required name="txtFullName" placeholder="Full Name"/>
							<input type="email" required name="txtEmail" placeholder="Email Address"/>
							<input type="phoneNo" required name="txtPhoneNo" placeholder="Phone No"/>
							<input type="password" required name="txtPassword" placeholder="Password"/>
							<input type="password" required data-rule-equalTo="#txtPassword" name="txtConfirmPassword" placeholder="Confirm Password"/>
							<button type="submit" name="btnSignup" class="btn btn-default">Signup</button>
						</form>
					</div><!--/sign up form-->
				</div>
			</div>
		</div>
	</section><!--/form-->
	
	
	<jsp:include page="/footer.jsp" />
</body>
</html>
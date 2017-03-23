<%@page import="com.emmaoyoita.eshop.models.dto.Product"%>
<%@page import="com.emmaoyoita.eshop.models.dto.Brand"%>
<%@page import="java.util.ListIterator"%>
<%@page import="com.emmaoyoita.eshop.models.dto.ProductCategory"%>
<%@page import="java.util.List"%>
<%
    if(request.getAttribute("parentCats") == null){
        request.getRequestDispatcher("/shop").forward(request, response);
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
								<li class="dropdown"><a href="#" class="active">Shop<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="shop.jsp" class="active">Products</a></li>
										<li><a href="checkout.jsp">Checkout</a></li> 
										<li><a href="cart.jsp">Cart</a></li> </ul>
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
	
	<section id="advertisement">
		<div class="container">
			<img src="images/shop/advertisement.jpg" alt="" />
		</div>
	</section>
	
	<section>
		<div class="container">
			<div class="row">
				<jsp:include page="/side.jsp" />
				<div class="col-sm-9 padding-right">
					<div class="features_items"><!--features_items-->
						<h2 class="title text-center">Store Items</h2>
						
                                    <%
                                        List <Product>products = (List)request.getAttribute("products");
                                        ListIterator <Product>productsIterator = products.listIterator();
                                        while(productsIterator.hasNext()) {
                                            Product product = productsIterator.next();
                                                                  %>
						
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<img height="249" width="268" src="img/uploads/<%= product.getImageUrl() %>" alt="<%= product.getName() %>" />
										<h2>$<%= product.getUnitPrice() %></h2>
										<p><%= product.getName() %></p>
										<a href="cart.jsp?pid=<%= product.getId() %>" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
									</div>
									<div class="product-overlay">
										<div class="overlay-content">
											<h2>$<%= product.getUnitPrice() %></h2>
											<p><%= product.getName() %></p>
                                            <p><%= product.getDescription() %></p>
											<a href="cart.jsp?pid=<%= product.getId() %>" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
										</div>
									</div>
								</div>
								<div class="choose">
									<ul class="nav nav-pills nav-justified">
										<li><a href="wishlist.jsp?pid=<%= product.getId() %>"><i class="fa fa-plus-square"></i>Add to wishlist</a></li>
										<li><a href="product-details.jsp?pid=<%= product.getId() %>"><i class="fa fa-plus-square"></i>View details</a></li>
									</ul>
								</div>
							</div>
						</div>
						
                    <% } %>
					</div><!--features_items-->
				
					
				</div>
			</div>
		</div>
	</section>
	
	<jsp:include page="/footer.jsp" />
</body>
</html>
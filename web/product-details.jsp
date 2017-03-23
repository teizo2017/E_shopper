<%@page import="java.util.ListIterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="com.emmaoyoita.eshop.models.dto.Product"%>
<%
    if(request.getAttribute("product") == null){
        request.getRequestDispatcher("/product-details").forward(request, response);
    }
    Product product = (Product)request.getAttribute("product");
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
                                        <li><a href="shop.jsp">Products</a></li>
										<li><a href="product-details.jsp" class="active">Product Details</a></li> 
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
	
	<section>
		<div class="container">
			<div class="row">
				<jsp:include page="/side.jsp" />
				<div class="col-sm-9 padding-right">
					<div class="product-details"><!--product-details-->
						<div class="col-sm-5">
							<div class="view-product">
								<img width="266" height="381" src="img/uploads/<%= product.getImageUrl()%>" alt="<%= product.getName()%>" />
								<h3>ZOOM</h3>
							</div>
							

						</div>
						<div class="col-sm-7">
							<div class="product-information"><!--/product-information-->
								<h2><%= product.getName()%></h2>
								<p>Stock No: <%= product.getStockNo()%></p>
								<span>
									<span>$<%= product.getUnitPrice()%></span>
									<a href="cart.jsp?pid=<%= product.getId()%>" class="btn btn-fefault cart">
										<i class="fa fa-shopping-cart"></i>
										Add to cart
									</a>
								</span>
								<p><b>Description:</b> <%= product.getDescription()%></p>
								<p><b>Category:</b> <%= product.getCategory().getName()%></p>
								<p><b>Brand:</b> <%= product.getBrand().getName()%></p>
								<a href=""><img src="images/product-details/share.png" class="share img-responsive"  alt="" /></a>
							</div><!--/product-information-->
						</div>
					</div><!--/product-details-->
					
					
					<div class="recommended_items"><!--recommended_items-->
						<h2 class="title text-center">recommended items</h2>
						
						<div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
							<div class="carousel-inner">
                            <div class="item active">
							 <%
                                                    List <Product>recommendedProducts = (List)request.getAttribute("recommendedProducts");
                                                    Collections.shuffle(recommendedProducts);
                                                    ListIterator recommendedIterator = recommendedProducts.listIterator();
                                                    int counter = 0;
                                                    while(recommendedIterator.hasNext())
                                                    {
                                                        if(counter >= 12)
                                                            break;
                                                        
                                                        product = (Product)recommendedIterator.next();
                                                        
                                                        if(counter++ % 3 == 0 && counter > 3){
						 %>
                                     	</div>
										<div class="item">	
									<% } %>
                                    <div class="col-sm-4">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<img width="268" height="134" src="img/uploads/<%= product.getImageUrl()%>" alt="<%= product.getName()%>" />
													<h2>$<%= product.getUnitPrice()%></h2>
													<p><%= product.getName()%></p>
													<a href="cart.jsp?pid=<%= product.getId()%>" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
												</div>
												
											</div>
										</div>
									</div>
									
							
                            <% }%></div>
							</div>
							 <a class="left recommended-item-control" href="#recommended-item-carousel" data-slide="prev">
								<i class="fa fa-angle-left"></i>
							  </a>
							  <a class="right recommended-item-control" href="#recommended-item-carousel" data-slide="next">
								<i class="fa fa-angle-right"></i>
							  </a>			
						</div>
					</div><!--/recommended_items-->
					
				</div>
			</div>
		</div>
	</section>
	
	<jsp:include page="/footer.jsp" />
</body>
</html>
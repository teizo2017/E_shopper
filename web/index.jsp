<%@page import="com.emmaoyoita.eshop.models.dto.Product"%>
<%@page import="java.util.Collections"%>
<%@page import="com.emmaoyoita.eshop.models.dto.Brand"%>
<%@page import="java.util.ListIterator"%>
<%@page import="com.emmaoyoita.eshop.models.dto.ProductCategory"%>
<%@page import="java.util.List"%>
<%
    if(request.getAttribute("parentCats") == null){
        request.getRequestDispatcher("/store-front").forward(request, response);
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
								<li><a href="index.jsp" class="active">Home</a></li>
								<li class="dropdown"><a href="#">Shop<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="shop.jsp">Products</a></li>
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
	
	<section id="slider"><!--slider-->
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div id="slider-carousel" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
							<li data-target="#slider-carousel" data-slide-to="1"></li>
							<li data-target="#slider-carousel" data-slide-to="2"></li>
						</ol>
						
						<div class="carousel-inner">
							<div class="item active">
								<div class="col-sm-6">
									<h1><span>E</span>-SHOPPER</h1>
									<h2>Everything in One Place</h2>
									<p>Shop for quality products from best designers around the world in one place. </p>
									<button type="button" class="btn btn-default get">Get it now</button>
								</div>
								<div class="col-sm-6">
									<img src="images/home/girl1.jpg" class="girl img-responsive" alt="" />
									<img src="images/home/pricing.png"  class="pricing" alt="" />
								</div>
							</div>
							<div class="item">
								<div class="col-sm-6">
									<h1><span>E</span>-SHOPPER</h1>
									<h2>100% Quality Designs</h2>
									<p>Shop for quality products from best designers around the world in one place. </p>
									<button type="button" class="btn btn-default get">Get it now</button>
								</div>
								<div class="col-sm-6">
									<img src="images/home/girl2.jpg" class="girl img-responsive" alt="" />
									<img src="images/home/pricing.png"  class="pricing" alt="" />
								</div>
							</div>
							
							<div class="item">
								<div class="col-sm-6">
									<h1><span>E</span>-SHOPPER</h1>
									<h2>One Stop Shop</h2>
									<p>Shop for quality products from best designers around the world in one place. </p>
									<button type="button" class="btn btn-default get">Get it now</button>
								</div>
								<div class="col-sm-6">
									<img src="images/home/girl3.jpg" class="girl img-responsive" alt="" />
									<img src="images/home/pricing.png" class="pricing" alt="" />
								</div>
							</div>
							
						</div>
						
						<a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
							<i class="fa fa-angle-left"></i>
						</a>
						<a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
							<i class="fa fa-angle-right"></i>
						</a>
					</div>
					
				</div>
			</div>
		</div>
	</section><!--/slider-->
	
	<section>
		<div class="container">
			<div class="row">
				<jsp:include page="/side.jsp" />
				<div class="col-sm-9 padding-right">
					<div class="features_items"><!--features_items-->
						<h2 class="title text-center">Features Items</h2>
						
						
						<%
                                                    List <Product>featuredProducts = (List)request.getAttribute("featuredProducts");
                                                    Collections.shuffle(featuredProducts);
                                                    ListIterator featuredIterator = featuredProducts.listIterator();
                                                    int counter = 0;
                                                    while(featuredIterator.hasNext())
                                                    {
                                                        if(counter++ >= 9)
                                                            break;
                                                        
                                                        Product product = (Product)featuredIterator.next();
						 %>
						
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<img height="249" width="268" src="img/uploads/<%= product.getImageUrl()%>" alt="<%= product.getName()%>" />
										<h2>$<%= product.getUnitPrice()%></h2>
										<p><%= product.getName()%></p>
										<a href="cart.php?pid=<?PHP echo $product['id'] ?>" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
									</div>
									<div class="product-overlay">
										<div class="overlay-content">
											<h2>$<%= product.getUnitPrice()%></h2>
											<p><%= product.getName()%></p>
                                            <p><%= product.getDescription()%></p>
											<a href="cart.jsp?pid=<%= product.getId()%>" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
										</div>
									</div>
								</div>
								<div class="choose">
									<ul class="nav nav-pills nav-justified">
										<li><a href="wishlist.jsp?pid=<%= product.getId()%>"><i class="fa fa-plus-square"></i>Add to wishlist</a></li>
										<li><a href="product-details.jsp?pid=<%= product.getId()%>"><i class="fa fa-plus-square"></i>View details</a></li>
									</ul>
								</div>
							</div>
						</div>
						
                    <% } %>
					</div><!--features_items-->
					
                    
					<div class="category-tab"><!--category-tab-->
						<div class="col-sm-12">
							<ul class="nav nav-tabs">
                           
						<%
                                                    counter = 0;
                                                    List <ProductCategory>catsWithProducts = (List)request.getAttribute("catsWithProducts");
                                                    Collections.shuffle(catsWithProducts);
                                                    ListIterator catIterator = catsWithProducts.listIterator();
                                                    String active_tab = "active";
                                                    
                                                    while(catIterator.hasNext()) {
                                                        if(counter++ >= 5)
                                                            break;
                                                        
                                                        ProductCategory catWithProducts = (ProductCategory)catIterator.next();
                                                        %>
                                		<li class="<%= active_tab %>"><a href="#category-slide<%= catWithProducts.getId()%>" data-toggle="tab"><%= catWithProducts.getName()%></a></li>
                                <%
									active_tab = "";
								} %>
							</ul>
						</div>
						<div class="tab-content">
								<% 
                                                                        catIterator = catsWithProducts.listIterator();
									active_tab = "active";
									while(catIterator.hasNext()) {
                                                                            ProductCategory catWithProducts = (ProductCategory)catIterator.next();
                                                                        
								%>
								
								<div class="tab-pane fade <%= active_tab%> in" id="category-slide<%= catWithProducts.getId()%>" >
                                
                                <%
									active_tab = "";
									
									List <Product>catProducts = catWithProducts.getProducts();
									Collections.shuffle(catProducts);
                                                                        ListIterator catProductsIterator = catProducts.listIterator();
									
									counter = 0;	
									while(catProductsIterator.hasNext())
									{
                                                                            Product catProduct = (Product)catProductsIterator.next();
                                                                            if(counter++ >= 4)
                                                                                break;
								 %>
								<div class="col-sm-3">
									<div class="product-image-wrapper">
										<div class="single-products">
											<div class="productinfo text-center">
												<img width="208" height="183" src="img/uploads/<%= catProduct.getImageUrl()%>" alt="<%= catProduct.getName()%>" />
												<h2>$<%= catProduct.getUnitPrice()%></h2>
												<p><%= catProduct.getName()%></p>
												<a href="cart.jsp?pid=<%= catProduct.getId()%>" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
											</div>
											
										</div>
									</div>
								</div>
                                <% } %>
                                
							</div>
							
							<% } %>
						</div>
					</div><!--/category-tab-->
					
					<div class="recommended_items"><!--recommended_items-->
						<h2 class="title text-center">recommended items</h2>
						
						<div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
							<div class="carousel-inner">
                            <div class="item active">
							 <%
                                                    List <Product>recommendedProducts = (List)request.getAttribute("recommendedProducts");
                                                    Collections.shuffle(recommendedProducts);
                                                    ListIterator recommendedIterator = recommendedProducts.listIterator();
                                                    counter = 0;
                                                    while(recommendedIterator.hasNext())
                                                    {
                                                        if(counter >= 12)
                                                            break;
                                                        
                                                        Product product = (Product)recommendedIterator.next();
                                                        
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
<%@page import="java.util.ListIterator"%>
<%@page import="com.emmaoyoita.eshop.models.dto.Wishlist"%>
<%@page import="java.util.List"%>

<%
    if(request.getAttribute("wishlist") == null){
        request.getRequestDispatcher("/wishlist").forward(request, response);
    }
    
    List<Wishlist> list = (List)request.getAttribute("wishlist");
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
										<li><a href="cart.jsp" class="active">Cart</a></li> 
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

	<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
				  <li><a href="#">Home</a></li>
				  <li class="active">Wishlist</li>
				</ol>
			</div>
			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Item</td>
							<td class="description"></td>
							<td class="price">Price</td>
							<td class="price">Date and Time</td>
							<td></td>
						</tr>
					</thead>
					<tbody>
						<%
                                                    ListIterator<Wishlist> wishlistIterator = list.listIterator();
						
							int total = 0;
							while(wishlistIterator.hasNext())
							{
                                                            Wishlist wishlist = wishlistIterator.next();
						 %>
						<tr>
							<td class="cart_product">
                                                            <a href="product-details.jsp?pid=<%= wishlist.getProduct().getId() %>"><img width="110" height="110" src="img/uploads/<%= wishlist.getProduct().getImageUrl() %>" alt="<%= wishlist.getProduct().getName() %>"></a>
							</td>
							<td class="cart_description">
								<h4><a href="product-details.jsp?pid=<%= wishlist.getProduct().getId() %>"><%= wishlist.getProduct().getName() %></a></h4>
								<p>Stock No: <%= wishlist.getProduct().getStockNo() %></p>
							</td>
							<td class="cart_price">
								<p>$<%= wishlist.getProduct().getUnitPrice() %></p>
							</td>
							<td class="cart_price">
								<p><%= wishlist.getDate() %></p>
							</td>
							<td class="cart_delete">
								<a class="cart_quantity_delete" href="wishlist.jsp?apid=<%= wishlist.getProduct().getId() %>"><i class="fa fa-times"></i></a>
							</td>
						</tr>
                        <%
						
						} %>
                        
					</tbody>
				</table>
			</div>
		</div>
	</section> <!--/#cart_items-->


	<jsp:include page="/footer.jsp" />
</body>
</html>
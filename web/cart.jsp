<%@page import="java.util.ListIterator"%>
<%@page import="com.emmaoyoita.eshop.models.dto.ShoppingCart"%>
<%@page import="java.util.List"%>
<%
    if(request.getAttribute("cart") == null){
        request.getRequestDispatcher("/cart").forward(request, response);
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
				  <li><a href="index.jsp">Home</a></li>
				  <li class="active">Shopping Cart</li>
				</ol>
			</div>
			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Item</td>
							<td class="description"></td>
							<td class="price">Price</td>
							<td class="quantity">Quantity</td>
							<td class="total">Total</td>
							<td></td>
						</tr>
					</thead>
					<tbody>
						<% 
							
                                                        List <ShoppingCart>cart = (List)request.getAttribute("cart");
                                                        ListIterator <ShoppingCart>cartIterator = cart.listIterator();
							int total = 0;
							while(cartIterator.hasNext())
							{
                                                            ShoppingCart item = cartIterator.next();                                                        
						 %>
						<tr>
							<td class="cart_product">
								<a href="javascript:void()"><img width="110" height="110" src="img/uploads/<%= item.getProduct().getImageUrl()%>" alt="<%= item.getProduct().getName()%>"></a>
							</td>
							<td class="cart_description">
								<h4><a href="product-details.jsp?pid=<%= item.getProduct().getId()%>"><%= item.getProduct().getName()%></a></h4>
								<p>Stock No: <%= item.getProduct().getStockNo()%></p>
							</td>
							<td class="cart_price">
								<p>$<%= item.getProduct().getUnitPrice()%></p>
							</td>
							<td class="cart_quantity">
								<div class="cart_quantity_button">
									<a class="cart_quantity_down" href="cart.jsp?apid=<%= item.getProduct().getId()%>&a=sub"> - </a>
									<input class="cart_quantity_input" type="text" name="quantity" value="<%= item.getQuantity()%>" autocomplete="off" size="2">
									<a class="cart_quantity_up" href="cart.jsp?apid=<%= item.getProduct().getId()%>&a=add"> + </a>
								</div>
							</td>
							<td class="cart_total">
								<p class="cart_total_price">$<% 
                                                                    double unitPrice = item.getProduct().getUnitPrice().doubleValue();
                                                                    out.println(unitPrice * item.getQuantity()); %></p>
							</td>
							<td class="cart_delete">
								<a class="cart_quantity_delete" href="cart.jsp?apid=<%= item.getProduct().getId()%>&a=del"><i class="fa fa-times"></i></a>
							</td>
						</tr>
                        <% 
							total += unitPrice*item.getQuantity();
						
						} %>
                        
                        <tr>
							<td colspan="4">&nbsp;</td>
							<td colspan="2">
								<table class="table table-condensed total-result">
									<tr>
										<td>Cart Sub Total</td>
										<td>$<%= total %></td>
									</tr>
									<tr class="shipping-cost">
										<td>Shipping Cost</td>
										<td>Free</td>										
									</tr>
									<tr>
										<td>Total</td>
										<td><span>$<%= total %></span></td>
									</tr>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
                <a class="btn btn-default check_out pull-left" href="checkout.jsp">Check Out</a>
                <a class="btn btn-default check_out pull-right" href="checkout.jsp">Check Out</a>
		</div>
	</section> <!--/#cart_items-->

	<section id="do_action">
		
	</section><!--/#do_action-->

	<jsp:include page="/footer.jsp" />
</body>
</html>
<%@page import="com.emmaoyoita.eshop.models.dto.State"%>
<%@page import="java.util.ListIterator"%>
<%@page import="com.emmaoyoita.eshop.models.dto.ShoppingCart"%>
<%@page import="java.util.List"%>
<%
    if(request.getAttribute("cart") == null){
        request.getRequestDispatcher("/checkout").forward(request, response);
    }
%>
<jsp:include page="/header.jsp" />
    <link href="cardjs/card-js.min.css" rel="stylesheet" type="text/css" />
	
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
										<li><a href="checkout.jsp" class="active">Checkout</a></li> 
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
	
	<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
				  <li><a href="#">Home</a></li>
				  <li class="active">Check out</li>
				</ol>
			</div><!--/breadcrums-->
			<form method="post" action="checkout-complete.jsp">
                            <% if(request.getSession().getAttribute("customerId") != null){%>
			
			<div class="checkout-options">
				<ul class="nav">
					<li>
						<a href="checkout.jsp?cancel"><i class="fa fa-times"></i>Cancel</a>
					</li>
				</ul>
                <p> </p>
			</div><!--/checkout-options-->
			<% }else{%>
            <div class="step-one">
				<h2 class="heading">Step1</h2>
			</div>
			<div class="checkout-options">
				<h3>New User</h3>
				<p>Checkout options</p>
				<ul class="nav">
					<li>
						<label><input name="rdCustomerType" type="radio" checked value="Register"> Register Account</label>
					</li>
					<li>
						<label><input name="rdCustomerType" type="radio" value="Guest"> Guest Checkout</label>
					</li>
					<li>
						<a href="checkout.jsp?cancel"><i class="fa fa-times"></i>Cancel</a>
					</li>
				</ul>
			</div><!--/checkout-options-->

			<div class="register-req">
				<p>Please use Register And Checkout to easily get access to your order history, or use Checkout as Guest</p>
			</div><!--/register-req-->
            <% }%>
			<div class="shopper-informations">
                            <div class="row">
                                <div class="col-sm-12 clearfix">
                                    <div class="bill-to">
                                        <p>Your Card Detail</p>
                                        <div style="margin-bottom: 50px">
                                            <div class="card-js" data-capture-name="true"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
				<div class="row">
					<div class="col-sm-8 clearfix">
						<div class="bill-to">
							<p>Bill To</p>
							<div class="form-one">
									<input type="text" name="txtCompanyName" placeholder="Company Name">
									<input type="text" required name="txtEmail" placeholder="Email*">
									<input type="text" name="txtTitle" placeholder="Title">
									<input type="text" name="txtFirstName" required placeholder="First Name *">
									<input type="text" name="txtMiddleName" placeholder="Middle Name">
									<input type="text" required name="txtLastName" placeholder="Last Name *">
							</div>
							<div class="form-two">
								  <select name="downState" required>
								    <option value="">-- State --</option>
                                                                            <% 
										List <State>states = (List)request.getAttribute("states");
                                                                                ListIterator <State>statesIterator = states.listIterator();
										while(statesIterator.hasNext())
										{
                                                                                    State state = statesIterator.next();
                                                                            %>
										<option value="<%= state.getId()%>"><%= state.getName()%></option>
                                                                            <% }%>
									</select>
									<input type="text" required name="txtAddress" placeholder="Address *">
									<input type="text" required name="txtPhoneNo" placeholder="Phone No *">
									<input type="text" name="txtFax" placeholder="Fax">
								
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="order-message">
							<p>Shipping Order</p>
							<textarea name="txtSpecialNote" placeholder="Notes about your order, Special Notes for Delivery" rows="16"></textarea>
							<label><input required name="cbIsAgreed" type="checkbox"> Shipping to bill address</label>
						</div>	
                            <input type="submit" name="btnCheckout" class="btn btn-primary pull-right" value="Continue Checkout">
					</div>				
				</div>
			</div>
			<div class="review-payment">
				<h2>Review & Payment</h2>
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
			<div class="payment-options">
                    <input type="submit" name="btnCheckout" class="btn btn-primary pull-right" value="Continue Checkout">
				</div>
		</div>
	</form>
	</section> <!--/#cart_items-->

	<jsp:include page="/footer.jsp" />
        <script src="cardjs/card-js.min.js"></script>
</body>
</html>
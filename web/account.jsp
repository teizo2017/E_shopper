<%@page import="com.emmaoyoita.eshop.models.dto.InvoiceDetail"%>
<%@page import="java.util.ListIterator"%>
<%@page import="com.emmaoyoita.eshop.models.dto.Invoice"%>
<%@page import="java.util.List"%>
<%@page import="com.emmaoyoita.eshop.models.dto.Customer"%>
<%
    if(request.getAttribute("customer") == null){
        request.getRequestDispatcher("/customer-account").forward(request, response);
    }
    
    Customer customer = (Customer)request.getAttribute("customer");
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
			<div class="row">
				<div class="col-sm-6">
					<div class="signup-form"><!--sign up form-->
						<h2>Your Account!</h2>
						<form action="<%= request.getContextPath() %>/customer-account" method="post">
							<input type="text" required value="<%= customer.getFullName()%>" name="txtFullName" placeholder="Full Name"/>
							<input type="email" required value="<%= customer.getEmail()%>" name="txtEmail" placeholder="Email Address"/>
							<input type="phoneNo" required value="<%= customer.getPhoneNo()%>" name="txtPhoneNo" placeholder="Phone No"/>
							<input type="password" value="<%= customer.getPassword()%>" required name="txtPassword" placeholder="Password"/>
							<input type="password" value="<%= customer.getPassword()%>" required data-rule-equalTo="#txtPassword" name="txtConfirmPassword" placeholder="Confirm Password"/>
							<button type="submit" name="btnUpdate" class="btn btn-default">Update</button>
						</form>
					</div><!--/sign up form-->
				</div>
			</div>
		</div>
	</section><!--/form-->
	
	<section id="cart_items">
		<div class="container">			
			<div class="review-payment">
				<h2>Your Purchase History</h2>
			</div>

			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
                        <tr>
                          <th style="width: 5%">S/N</th>
                          <th style="width: 15%">Customer Name</th>
                          <th style="width: 10%">Invoice No</th>
                          <th style="width: 10%">Payment Type</th>
                          <th style="width: 10%">Date</th>
                          <th style="width: 20%">Billing Detail</th>
                          <th style="width: 20%">Items</th>
                        </tr>
                    </thead>
					<tbody>
                    <%
                                               
                    List<Invoice> invoices = (List)request.getAttribute("invoices");
                    ListIterator<Invoice> invoicesIterator = invoices.listIterator();
                    int counter = 1;
                    while(invoicesIterator.hasNext()) {
                        Invoice invoice = invoicesIterator.next();
                        String billing = "";

                        billing += "Company Name: "+invoice.getBilling().getCompanyName()+"<br/>";
                        billing += "Full Name: "+ invoice.getBilling().getTitle()+" "+invoice.getBilling().getFirstName()+" "+invoice.getBilling().getMiddleName()+
                                    invoice.getBilling().getLastName()+"<br/>";
                        billing += "Email: "+invoice.getBilling().getEmail()+"<br/>";
                        billing += "Address: "+invoice.getBilling().getAddress()+"<br/>";
                        billing += "Phone No: "+invoice.getBilling().getPhoneNo()+"<br/>";
                        billing += "Fax: "+invoice.getBilling().getFax()+"<br/>";
                        billing += "Special Note: "+invoice.getBilling().getSpecialNote()+"<br/>";
                    %>
                      <tr class="table-flag-blue">
                          <td><%= counter++ %></td>
                          <td><%= customer.getFullName() %></td>
                          <td><%= invoice.getInvoiceNo() %></td>
                          <td><%= invoice.getPaymentType() %></td>
                          <td><%= invoice.getDate() %></td>
                          <td><%= billing %></td>
                          <td><%
                            
                            String invoiceDetailStr = "";
                            
                            ListIterator<InvoiceDetail> invoiceDetailItr = invoice.getInvoiceDetail().listIterator();
                            double totalAmount = 0;
                            while(invoiceDetailItr.hasNext())
                            {
                                InvoiceDetail invoiceDetail = invoiceDetailItr.next();
                                invoiceDetailStr = invoiceDetail.getProduct().getName()+" [Qty: "+invoiceDetail.getQuantity()+", N"+invoiceDetail.getTotalAmount().doubleValue()+"]<br/>";
                                totalAmount += invoiceDetail.getTotalAmount().doubleValue();
                            }
                            out.println(invoiceDetailStr+"<br/><strong>Total Amount: N"+totalAmount+"</strong>");
                           %></td>
                      </tr>
                    <% } %>
            
                </tbody>
				</table>
			</div>
			
		</div>
	</section> <!--/#cart_items-->
    
	<jsp:include page="/footer.jsp" />
</body>
</html>
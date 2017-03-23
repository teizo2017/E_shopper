<%@page import="com.emmaoyoita.eshop.models.dto.InvoiceDetail"%>
<%@page import="com.emmaoyoita.eshop.models.dto.Customer"%>
<%@page import="com.emmaoyoita.eshop.models.dao.CustomerDAO"%>
<%@page import="java.util.ListIterator"%>
<%@page import="com.emmaoyoita.eshop.models.dto.Invoice"%>
<%@page import="java.util.List"%>
<%
    if(request.getAttribute("invoices") == null){
        request.getRequestDispatcher("/admin/invoices").forward(request, response);
    }
    else
    //allow access only if session exists
    if(session.getAttribute("user") == null){
        response.sendRedirect("login.jsp");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/admin/header.jsp" />
			
			<!-- start: Content -->
			<div id="content" class="span10">
			
			
			<ul class="breadcrumb">
				<li><small><h1>
					<i class="icon-file"></i>
					<a href="index.jsp">Home</a>  
					<i class="icon-angle-right"></i></small></h1>
				</li>
				<li><small><h1><a href="#">View Invoices</a></small></h1></li>
			</ul>

			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class=" icon-th"></i><span class="break"></span>Invoice Details</h2>
						<div class="box-icon">
							<a href="#" class="btn-setting"><i class="halflings-icon white wrench"></i></a>
							<a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
							<a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
						</div>
				
					</div>
					<div class="box-content">
				
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th>S/N</th>
								  <th>Customer Name</th>
								  <th>Invoice No</th>
								  <th>Date</th>
								  <th>Billing Detail</th>
								  <th>Items</th>
								  <th>Total Amount</th>
								  <th>Status</th>
							  </tr>
						  </thead>   
						  <tbody>
                                                      <%
                                                        List<Invoice> invoices = (List)request.getAttribute("invoices");
                                                        ListIterator<Invoice> invoicesIterator = invoices.listIterator();
                                                        int counter = 1;
                                                        
                                                        while(invoicesIterator.hasNext()) {
                                                            Invoice invoice = invoicesIterator.next();
                                                            String customerName = "";
                                                            String billing = "";
                                                            String invoiceDetailStr = "";
                                                            
                                                            if(invoice.getCustomerId() > 0)
                                                            {
                                                                Customer customer = CustomerDAO.fetch(invoice.getCustomerId());
                                                                customerName = customer.getFullName();
                                                            }

                                                            billing += "Company Name: "+invoice.getBilling().getCompanyName()+"<br/>";
                                                            billing += "Full Name: "+ invoice.getBilling().getTitle()+" "+invoice.getBilling().getFirstName()+" "+invoice.getBilling().getMiddleName()+
                                                                        " "+invoice.getBilling().getLastName()+"<br/>";
                                                            billing += "Email: "+invoice.getBilling().getEmail()+"<br/>";
                                                            billing += "Address: "+invoice.getBilling().getAddress()+"<br/>";
                                                            billing += "State: "+invoice.getBilling().getState().getName()+"<br/>";
                                                            billing += "Phone No: "+invoice.getBilling().getPhoneNo()+"<br/>";
                                                            billing += "Fax: "+invoice.getBilling().getFax()+"<br/>";
                                                            billing += "Special Note: "+invoice.getBilling().getSpecialNote()+"<br/>";
                                                            
                                                            List<InvoiceDetail> invoiceDetailList = invoice.getInvoiceDetail();
                                                            ListIterator<InvoiceDetail> invoiceDetailIterator = invoiceDetailList.listIterator();
                                                            while(invoiceDetailIterator.hasNext())
                                                            {
                                                                InvoiceDetail invoiceDetail = invoiceDetailIterator.next();
                                                                invoiceDetailStr += "Qty: "+invoiceDetail.getQuantity()+
                                                                                "<br/>Amount: "+ invoiceDetail.getTotalAmount().doubleValue()+
                                                                                 "<br/>Product: "+ invoiceDetail.getProduct().getName()+
                                                                                 "<br/>Stock No: "+ invoiceDetail.getProduct().getStockNo()+"<br/>----<br/>";
                                                            }
                                                            String deliveredStr = "<span class=\"label-success\">Delivered</span>";
                                                            if(!invoice.getIsDelivered())
                                                            {
                                                                deliveredStr = "<a href=\""+request.getContextPath()+"/admin/invoices?iid="+invoice.getId()+"\"><span class=\"label-warning\">Not delivered</span></a>";
                                                            }
                                                      %>
							<tr>
								<td><%= counter++ %></td>
                                                                <td class="center"><%= customerName %></td>
								<td class="center"><%= invoice.getInvoiceNo() %></td>
                                                                <td class="center"><%= invoice.getDate() %></td>
                                                                <td class="center"><%= billing %></td>
                                                                <td class="center"><%= invoiceDetailStr %></td>
                                                                <td class="center"><%= invoice.getTotalAmount().doubleValue() %></td>
                                                                <td class="center"><%= deliveredStr %></td>
							</tr>
                                                      <%}%>
						  </tbody>
					  </table>            
					</div>
				</div><!--/span-->
			
			</div>
			<!--/row-->
			
       

	</div><!--/.fluid-container-->
	
			<!-- end: Content -->
		</div><!--/#content.span10-->
		</div><!--/fluid-row-->
		
	<div class="modal hide fade" id="myModal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">Ã</button>
			<h3>Settings</h3>
		</div>
		<div class="modal-body">
			<p>Here settings can be configured...</p>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">Close</a>
			<a href="#" class="btn btn-primary">Save changes</a>
		</div>
	</div>
	
	<div class="common-modal modal fade" id="common-Modal1" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<ul class="list-inline item-details">
				<li><a href="http://themifycloud.com">Admin templates</a></li>
				<li><a href="http://themescloud.org">Bootstrap themes</a></li>
			</ul>
		</div>
	</div>
	
	<div class="clearfix"></div>
	
	<jsp:include page="/admin/footer.jsp" />
</body>
</html>

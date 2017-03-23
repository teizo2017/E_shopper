<%@page import="java.util.Iterator"%>
<%@page import="com.emmaoyoita.eshop.models.dto.Product"%>
<%@page import="java.util.List"%>
<%
    if(request.getAttribute("products") == null){
        request.getRequestDispatcher("/admin/products").forward(request, response);
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
				<li><small><h1><a href="#">View Products</a></small></h1></li>
			</ul>

			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class=" icon-th"></i><span class="break"></span>View Products</h2>
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
								  <th>Stock No</th>
								  <th>Name</th>
								  <th>Description</th>
								  <th>Brand</th>
								  <th>Category</th>
								  <th>Unit Price</th>
								  <th>Posted</th>
								  <th>Actions</th>
							  </tr>
						  </thead>   
						  <tbody>
                                                      <%
                                                          int counter = 1;
                                                          List products = (List)request.getAttribute("products");
                                                          Iterator <Product>iterator = products.listIterator();
                                                          while(iterator.hasNext()){
                                                              Product product = iterator.next();
                                                            
                                                      %>
							<tr>
								<td><%= counter++ %></td>
								<td class="center"><%= product.getStockNo() %></td>
								<td class="center"><%= product.getName()%></td>
								<td class="center"><%= product.getDescription() %></td>
								<td class="center"><%= product.getBrand().getName() %></td>
								<td class="center"><%= product.getCategory().getName() %></td>
								<td class="center"><%= product.getUnitPrice() %></td>
								<td class="center"><%= product.getCreated().toString() %></td>
								<td class="center">
									<!--a class="btn btn-success" href="#">
										<i class="halflings-icon white zoom-in"></i>  
									</a-->
									<a class="btn btn-info" href="<%= request.getContextPath()%>/admin/product?pid=<%= product.getId()%>">
										<i class="halflings-icon white edit"></i>  
									</a>
									<!--<a class="btn btn-danger" href="<%= request.getContextPath()%>/admin/products?bid=<%= product.getId()%>">
										<i class="halflings-icon white trash"></i> 
									</a>-->
								</td>
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

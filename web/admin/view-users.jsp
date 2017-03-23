<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.emmaoyoita.eshop.models.dto.User"%>
<%
    if(request.getAttribute("users") == null){
        request.getRequestDispatcher("/admin/users").forward(request, response);
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
				<li><small><h1><a href="#">Users Information</a></small></h1></li>
			</ul>

			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class=" icon-th"></i><span class="break"></span>All Users</h2>
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
								  <th>Full Name</th>
								  <th>User Name</th>
								  <th>Posted</th>
								  <th>Actions</th>
							  </tr>
						  </thead>   
						  <tbody>
                                                      <%
                                                          int counter = 1;
                                                          List users = (List)request.getAttribute("users");
                                                          Iterator <User>iterator = users.listIterator();
                                                          while(iterator.hasNext()){
                                                              User user = iterator.next();
                                                            
                                                      %>
							<tr>
								<td><%= counter++ %></td>
								<td class="center"><%= user.getFullName() %></td>
								<td class="center"><%= user.getUserName() %></td>
								<td class="center"><%= user.getCreated().toString() %></td>
								<td class="center">
									<a class="btn btn-info" href="<%= request.getContextPath()%>/admin/user?uid=<%= user.getId()%>">
										<i class="halflings-icon white edit"></i>  
									</a>
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
	
	<div class="clearfix"></div>
	
	<jsp:include page="/admin/footer.jsp" />
</body>
</html>

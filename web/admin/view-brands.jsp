<%@page import="com.emmaoyoita.eshop.models.dto.Brand"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%
    if(request.getAttribute("brands") == null){
        request.getRequestDispatcher("/admin/brands").forward(request, response);
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
				<li><small><h1><a href="#">View Brands</a></small></h1></li>
			</ul>

			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class=" icon-th"></i><span class="break"></span>View Brands</h2>
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
								  <th>Name</th>
								  <th>Description</th>
								  <th>Posted</th>
								  <th>Actions</th>
							  </tr>
						  </thead>   
						  <tbody>
                                                      <%
                                                          int counter = 1;
                                                          List brands = (List)request.getAttribute("brands");
                                                          Iterator <Brand>iterator = brands.listIterator();
                                                          while(iterator.hasNext()){
                                                              Brand brand = iterator.next();
                                                            
                                                      %>
							<tr>
								<td><%= counter++ %></td>
								<td class="center"><%= brand.getName() %></td>
                                                                <td class="center"><%= brand.getDescription() %> </td>
								<td class="center"><%= brand.getCreated().toString() %></td>
								<td class="center">
                                                                    <a class="btn btn-info" href="<%= request.getContextPath()%>/admin/brand?bid=<%= brand.getId()%>">
										<i class="halflings-icon white edit"></i>  
									</a>
									<!--<a class="btn btn-danger" href="<%= request.getContextPath()%>/admin/brands?bid=<%= brand.getId()%>">
										<i class="halflings-icon white trash"></i> 
									</a> -->
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
        <script language="javascript">
            $(function(){
                alert("chibex");
                $(".btn-danger").click(fuction(e){
                    e.preventDefault();
                    alert("chibexme");
                });
            });
        </script>
</body>
</html>

<%@page import="com.emmaoyoita.eshop.models.dto.User"%>
<%
    //allow access only if session exists
    if(session.getAttribute("user") == null){
        response.sendRedirect("login.jsp");
    }
    
    String fullName = "";  
    String userName = ""; 
    String password = "";
    String confirmPassword = "";
    User user = (User)request.getAttribute("user"); 
    String action = "Register";
    String queryString = "";
    if(user != null)
    {
        fullName = user.getFullName();
        userName = user.getUserName();
        password = user.getPassword();
        confirmPassword = user.getPassword();
        queryString = "?uid="+user.getId();
        action = "Update";
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
				<li><small><h1><a href="#">User Form</a></small></h1></li>
			</ul>
                        <% 
                            if(request.getAttribute("error") != null){
                                out.println("<h2 style=\"color:#FF0000\">");
                                out.println(request.getAttribute("error")); 
                                out.println("</h2>");
                            }
                            
                            if(request.getAttribute("message") != null){
                                out.println("<h2 style=\"color:#0000FF\">");
                                out.println(request.getAttribute("message")); 
                                out.println("</h2>");
                            }
                        %>
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class="halflings-icon white align-justify"></i><span class="break"></span>New User</h2>
						<div class="box-icon">
							<a href="#" class="btn-setting"><i class="halflings-icon white wrench"></i></a>
							<a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
							<a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
						</div>
					</div>
					<div class="box-content">
                                            <form class="form-horizontal" method="post" action="<%= request.getContextPath() %>/admin/user<%=queryString%>">
						  <fieldset>
							<div class="control-group">
							  <label class="control-label" for="txtFullName">Full Name</label>
							  <div class="controls">
                                                              <input type="text" class="span9 typeahead" required id="txtFullName" value="<%= fullName%>" name="txtFullName" >
							  </div>
							</div>

							<div class="control-group">
							  <label class="control-label" for="txtUserName">User Name </label>
							  <div class="controls">
                                                              <input type="text" class="span9 typeahead" required id="txtUserName"  value="<%= userName%>" name="txtUserName" colspan = "5">
                                                                <input type="hidden" id="txtUserId" name="txtUserId" value="<%out.println((session.getAttribute("userId")));%>"/>
							  </div>
							</div>
							
							<div class="control-group">
							  <label class="control-label" for="txtPassword">Password </label>
							  <div class="controls">
                                                              <input type="password" class="span9 typeahead" required id="txtPassword" value="<%= password%>" name="txtPassword">
							  </div>
							</div>

							<div class="control-group">
							  <label class="control-label" for="txtConfirmPassword">Confirm Password </label>
							  <div class="controls">
                                                              <input type="password" class="span9 typeahead" required id="txtConfirmPassword" value="<%= confirmPassword%>" name="txtConfirmPassword">
							  </div>
							</div>

							<div class="form-actions">
							  <button type="submit" class="btn btn-primary"><%= action %></button>
							  <button type="reset" class="btn">Cancel</button>
							</div>
						  </fieldset>
						</form>   

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

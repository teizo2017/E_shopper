<%@page import="com.emmaoyoita.eshop.models.dto.User"%>
<%
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
				<li><h1><small>
					<i class="icon-home"></i>
					<a href="index.jsp">Home</a> 
					<i class="icon-angle-right"></i>
				</li></small></h1>
				<li><h1><small>eStore Admin Dashboard</small></h1></li>
			</ul>
                            
                            <div class = "page-header" style="text-align: center">
                                <br/> <br/>
                                <h1>Welcome</h1>
                                <br/>
                                <h2><small>eStore Administrative Dashboard</small></h2>
                                <br/>
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

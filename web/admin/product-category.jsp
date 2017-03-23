<%@page import="util.HelperUtil"%>
<%@page import="java.util.ListIterator"%>
<%@page import="com.emmaoyoita.eshop.models.dto.ProductCategory"%>
<%@page import="java.util.List"%>
<%
    if(request.getAttribute("categories") == null){
        request.getRequestDispatcher("/admin/product-category").forward(request, response);
    }
    else
    //allow access only if session exists
    if(session.getAttribute("user") == null){
        response.sendRedirect("login.jsp");
    }
    
    String name = "";
    String description = "";
    ProductCategory category = (ProductCategory)request.getAttribute("category"); 
    int parentId = 0;
      
    String action = "Register";
    String queryString = "";
    if(category != null)
    {
        queryString = "?cid="+category.getId();
        name = category.getName();
        description = category.getDescription();
        parentId = category.getParentId();
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
				<li><small><h1><a href="#">Product Category Information</a></small></h1></li>
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
						<h2><i class="halflings-icon white align-justify"></i><span class="break"></span>Product Category Information</h2>
						<div class="box-icon">
							<a href="#" class="btn-setting"><i class="halflings-icon white wrench"></i></a>
							<a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
							<a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
						</div>
					</div>
					<div class="box-content">
                                            <form class="form-horizontal" method="post" action="<%= request.getContextPath() %>/admin/product-category<%=queryString%>">
						  <fieldset>
							<div class="control-group">
							  <label class="control-label" for="typeahead"> Parent Category </label>
							  <div class="controls">
                                                                <select name="downParent" id="downParent" class="span9 typeahead">
                                                                    <option value="0">Select parent</option>
                                                                    <%
                                                                        List categories = (List)request.getAttribute("categories");
                                                                        ListIterator iterator = categories.listIterator();
                                                                        while(iterator.hasNext())
                                                                        {
                                                                            ProductCategory cat = (ProductCategory)iterator.next();
                                                                            
                                                                            out.println("<option value=\""+cat.getId()+"\"");
                                                                            
                                                                            if(cat.getId() == parentId)
                                                                                out.println(" selected ");
                                                                            
                                                                            out.println(">"+cat.getName()+"</option>");
                                                                        }
                                                                    %>
                                                                </select>
							  </div>
							</div>

							<div class="control-group">
							  <label class="control-label" for="typeahead" > Product Category</label>
							  <div class="controls">
                                                              <input type="text" class="span9 typeahead" required placeholder="Enter category name" id="txtName" value="<%= name%>" name="txtName" >
                                                              <input type="hidden" id="txtUserId" name="txtUserId" value="<%out.println((session.getAttribute("userId")));%>"/>
							  </div>
							</div>

							<div class="control-group">
							  <label class="control-label" for="typeahead">Description </label>
							  <div class="controls">
								<textarea type="text" class="span9 typeahead" placeholder="Ender category description" name="txtDescription" id="txtDescription" colspan = "5"><%= description%></textarea>
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

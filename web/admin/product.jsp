<%@page import="com.emmaoyoita.eshop.models.dto.Product"%>
<%@page import="com.emmaoyoita.eshop.models.dto.Brand"%>
<%@page import="com.emmaoyoita.eshop.models.dto.ProductCategory"%>
<%@page import="java.util.ListIterator"%>
<%@page import="java.util.List"%>
<%
    if(request.getAttribute("categories") == null){
        request.getRequestDispatcher("/admin/product").forward(request, response);
    }
    else
    //allow access only if session exists
    if(session.getAttribute("user") == null){
        response.sendRedirect("login.jsp");
    }
    
    String stockNo = "";
    String name = "";
    String description = "";
    double unitPrice = 0;
    int brandId = 0;
    int categoryId = 0;
    boolean isRecommended = false;
    boolean isFeatured = false;
    
    Product product = (Product)request.getAttribute("product");    
    String action = "Register";
    String queryString = "";
    if(product != null){
        product = (Product)request.getAttribute("product");
        
        stockNo = product.getStockNo();
        name = product.getName();
        description = product.getDescription();
        unitPrice = product.getUnitPrice().doubleValue();
        brandId = product.getBrand().getId();
        categoryId = product.getCategory().getId();
        isRecommended = product.getIsRecommended();
        isFeatured = product.getIsFeatured();
        
        queryString = "?pid="+product.getId();
        action = "Update";
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/admin/header.jsp" />
			
			<!-- start: Content -->
			<div id="content" class="span10">
			
			
			<ul class="breadcrumb">
				<li><small><h1>
					<i class="icon-home"></i>
					<a href="index.jsp">Home</a>  
					<i class="icon-angle-right"></i></small></h1>
				</li>
				<li><small><h1><a href="#">Product Information</a></small></h1></li>
				<p><small><h3>Register or edit product information</small></h3></p>
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
						<h2><i class="halflings-icon white align-justify"></i><span class="break"></span>Product Information</h2>
						<div class="box-icon">
							<a href="#" class="btn-setting"><i class="halflings-icon white wrench"></i></a>
							<a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
							<a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
						</div>
					</div>
					<div class="box-content">
                                            <form class="form-horizontal" method="post" action="<%= request.getContextPath() %>/admin/product<%=queryString%>" enctype="multipart/form-data">
						  <fieldset>
						  	<div class="control-group">
								<label class="control-label" for="selectError9">Brand</label>
								<div class="controls">
								  <select name="downBrand" id="downBrand" class="span9 typeahead">
                                                                    <%
                                                                        List brands = (List)request.getAttribute("brands");
                                                                        ListIterator iterator = brands.listIterator();
                                                                        while(iterator.hasNext())
                                                                        {
                                                                            Brand brand = (Brand)iterator.next();
                                                                            
                                                                            out.println("<option value=\""+brand.getId()+"\"");
                                                                            
                                                                            if(brand.getId() == brandId)
                                                                                out.println(" selected ");
                                                                            
                                                                            out.println(">"+brand.getName()+"</option>");
                                                                        }
                                                                    %>
                                                                </select>
								</div>
							  </div>

							  <div class="control-group">
								<label class="control-label" for="selectError3">Product Category</label>
								<div class="controls">
								  <select name="downCategory" id="downCategory" class="span9 typeahead">
                                                                    <%
                                                                        List categories = (List)request.getAttribute("categories");
                                                                        iterator = categories.listIterator();
                                                                        while(iterator.hasNext())
                                                                        {
                                                                            ProductCategory cat = (ProductCategory)iterator.next();
                                                                            
                                                                            out.println("<option value=\""+cat.getId()+"\"");
                                                                            
                                                                            if(cat.getId() == categoryId)
                                                                                out.println(" selected ");
                                                                            
                                                                            out.println(">"+cat.getName()+"</option>");
                                                                        }
                                                                    %>
                                                                </select>
								</div>
							  </div>

							<div class="control-group">
							  <label class="control-label" for="typeahead">Stock No </label>
							  <div class="controls">
                                                              <input type="number" class="span9 typeahead" required placeholder="Enter stock no" id="txtStockNo" value="<%= stockNo%>" name="txtStockNo" >
							  </div>
							</div>

							<div class="control-group">
							  <label class="control-label" for="typeahead">Product </label>
							  <div class="controls">
								<input type="text" class="span9 typeahead" required placeholder="Enter product name" id="txtName" value="<%= name%>" name="txtName" >
                                                                <input type="hidden" id="txtUserId" name="txtUserId" value="<%out.println((session.getAttribute("userId")));%>"/>
							  </div>
							</div>

							<div class="control-group">
							  <label class="control-label" for="typeahead">Description </label>
							  <div class="controls">
								<textarea type="text" class="span9 typeahead" placeholder="Ender product description" name="txtDescription" id="txtDescription" colspan = "5"><%= description%></textarea>
							  </div>
							</div>

							<div class="control-group">
							  <label class="control-label" for="typeahead">Unit Price </label>
							  <div class="controls">
								<input type="number" class="span9 typeahead" required placeholder="Enter unit price" id="txtUnitPrice" value="<%= unitPrice%>" name="txtUnitPrice" >
							  </div>
							</div>

							<div class="control-group">
							  <label class="control-label" for="fileInput">Image</label>
							  <div class="controls">
								<input class="input-file uniform_on" id="flImage" name="flImage" type="file">
							  </div>
							</div> 

							 <div class="control-group">
								<label class="control-label" for="cbRecommended">Mark as recommended?</label>
								<div class="controls">
								  <label class="checkbox">
									<input type="checkbox" id="cbRecommended" <% if(isRecommended) out.println("checked"); %> name="cbRecommended" value="yes">
								  </label>
								</div>
							  </div>

							   <div class="control-group">
								<label class="control-label" for="cbFeatured">Mark as featured?</label>
								<div class="controls">
								  <label class="checkbox">
                                                                      <input type="checkbox" id="cbFeatured" <% if(isFeatured) out.println("checked"); %> name="cbFeatured" value="yes">
								  </label>
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

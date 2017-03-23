<%@page import="com.emmaoyoita.eshop.models.dto.Brand"%>
<%@page import="java.util.ListIterator"%>
<%@page import="com.emmaoyoita.eshop.models.dto.ProductCategory"%>
<%@page import="java.util.List"%>
<div class="col-sm-3">
					<div class="left-sidebar">
						<h2>Category</h2>
						<div class="panel-group category-products" id="accordian"><!--category-productsr-->
                        
				<%
                                    List <ProductCategory> parentCats = (List)request.getAttribute("parentCats");
                                    ListIterator iterator = parentCats.listIterator();
                                    while(iterator.hasNext()) {
					ProductCategory parentCat = (ProductCategory)iterator.next();
                                    
										if(parentCat.getSubCats().size() < 1)
									{
                                %>
                                          
                                     
                                          <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title"><a href="shop.jsp?cid=<%= parentCat.getId() %>"><%= parentCat.getName() %></a></h4>
                                            </div>
                                         <% }else{ %>
                                            <div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordian" href="#design<%= parentCat.getId() %>">
											<span class="badge pull-right"><i class="fa fa-plus"></i></span>
											<%= parentCat.getName() %>
										</a>
									</h4>
								</div>
                                            
                                            <%
										 }
								  	if(parentCat.getSubCats().size() > 0)
									{
										%>
                                        <div id="design<%= parentCat.getId() %>" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
                                        <%
										ListIterator subIterator = parentCat.getSubCats().listIterator();
										while(subIterator.hasNext()) { 
                                                                                    ProductCategory subCat = (ProductCategory)subIterator.next();
                                        %>
											  
											<li><a href="shop.jsp?cid=<%= subCat.getId() %>"><%= subCat.getName() %></a></li>												
												
										<% } %>
										</ul>
									</div>
								</div>
									<% } %>
                                            
                                        </div>
                                    <% } %>
							
							
						</div><!--/category-products-->
					
						<div class="brands_products"><!--brands_products-->
							<h2>Brands</h2>
							<div class="brands-name">
								<ul class="nav nav-pills nav-stacked">
								  <%
                                                                      List <Brand> brands = (List)request.getAttribute("brands");
                                                                      ListIterator brandsIterator = brands.listIterator();
                                    while(brandsIterator.hasNext()) {
                                        Brand brand = (Brand)brandsIterator.next();
                                                                  %>
                                          <li><a href="shop.jsp?bid=<%= brand.getId() %>"> <span class="pull-right">(<%= brand.getTotalProdcuts()%>)</span><%= brand.getName() %></a></li>
                                    <% } %>
								</ul>
							</div>
						</div><!--/brands_products-->
						
						<div class="price-range"><!--price-range-->
							<h2>Price Range</h2>
							<div class="well text-center">
								 <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600" data-slider-step="5" data-slider-value="[250,450]" id="sl2" ><br />
								 <b class="pull-left">$ 0</b> <b class="pull-right">$ 600</b>
							</div>
						</div><!--/price-range-->
						
						<div class="shipping text-center"><!--shipping-->
							<img src="images/home/shipping.jpg" alt="" />
						</div><!--/shipping-->
					
					</div>
				</div>
				
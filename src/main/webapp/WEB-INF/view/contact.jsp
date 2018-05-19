<div class="container">	
    

 <h1 class="my-4" style="color: green;">Add Permissions
      </h1>
      <hr style="border: 2px solid green" />
	<form action="/savePermissions" method="post">
	
	Select Category:<select id="fun1" name="url">
	<option value="">Select Model</option>
	<option value="home">Home</option>
	<option value="about">About</option>
	<option value="service">Service</option>
	<option value="contact">Contact</option>
	</select>
	<br>
	<br>
	<table class="table table-bordered table-condensed table-hover">
		<thead>
			<tr>
						<th style="text-align: center;">Roles</th>
												<th style="text-align: center;">View</th>
												<th style="text-align: center;">Create</th>
												<th style="text-align: center;">Edit </th>
												<th style="text-align: center;">Delete </th>
			</tr>
		</thead>

		<tbody id="tbd">
			<c:forEach items="${data}" var="a1" varStatus="loop">

				<tr>
					<td>${a1.userRoles.name}</td>
					
					
					<td align="center"><c:if test="${a1.view eq true}">
					<c:set var="view" value="checked='checked'"></c:set>
					</c:if>
					<label class="label"><input type="checkbox"  name="permissions[${loop.index}].view" ${view } >	
					<span class="checkmark"></span></label></td>
					
					
					<td><c:if test="${a1.create eq true}">
					<c:set var="create" value="checked='checked'"></c:set>
					</c:if>
					<label class="label"><input type="checkbox"  name="permissions[${loop.index}].create" ${create}> 	
					<span class="checkmark"></span></label></td>
					
					
					<td><c:if test="${a1.editAny eq true}">
					<c:set var="editAny" value="checked='checked'"></c:set>
					</c:if><label class="label"><input type="checkbox"  name="permissions[${loop.index}].editAny" ${editAny}> 	
					<span class="checkmark"></span></label></td>
					
					<td><c:if test="${a1.deleteAny eq true}">
					<c:set var="deleteAny" value="checked='checked'"></c:set>
					</c:if>
					<label class="label"><input type="checkbox"  name="permissions[${loop.index}].deleteAny" ${deleteAny}> 	
					<span class="checkmark"></span></label></td>
					
					<input type="hidden"
														name="permissions[${loop.index }].userRoles.id"
														value="${a1.userRoles.id}">
														
														<input type="hidden"
														name="permissions[${loop.index }].id"
														value="${a1.id}">
				</tr>
				<c:set var="view" value=""></c:set>
				<c:set var="create" value=""></c:set>
				<c:set var="editAny" value=""></c:set>
				<c:set var="deleteAny" value=""></c:set>
				
			</c:forEach> 
		</tbody>

	</table>
	</div>
	<input type="submit" value="SavePermission" />
	
</form>


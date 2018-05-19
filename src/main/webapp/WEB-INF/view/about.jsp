<div class="container">

	<h1 class="my-4" style="color: green;">Crud Operation using Spring
		Boot</h1>
	<hr style="border: 2px solid green" />
	<form action="/saveUser" method="post">
		<div class="row">
			<div class="col-md-12">
				name: &nbsp; &nbsp; &nbsp; &nbsp;<input type="text" name="name"
					value="${data1.name}">
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-md-12">
				password: <input type="text" name="password"
					value="${data1.password}">

				<c:if test="${! empty data1 }">
					<input type="hidden" name="id" value="${data1.id}">
				</c:if>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-md-12">
				<span>Select Roles:</span>
				<%-- 
				 <c:forEach items="${data2}" var="a1" varStatus="loop1">
				

				<label class="label">${a1.name}
 	 <input type="checkbox" name="roles" value="${a1.id}" >
  	<span class="checkmark"></span>
		</label>


				</c:forEach>  --%>
				
				
				
				<c:forEach items="${data2}" var="a1" varStatus="loop">
				
				<c:set var="no" value="0"></c:set>

														

					<c:forEach items="${data1.roles}" var="z1" varStatus="loop1">
					
					<c:if test="${z1.id eq a1.id }">
														<c:set var="no" value="1"></c:set>
														<label class="label">${a1.name}
 	 <input type="checkbox" name="roles[${loop.index}].id" value="${a1.id}" checked="checked">
  	<span class="checkmark"></span>
		</label>
														
														</c:if>


					</c:forEach>

				<c:if test="${no eq 0 }">
				<label class="label">${a1.name}
 	 <input type="checkbox" name="roles[${loop.index}].id" value="${a1.id}" >
  	<span class="checkmark"></span>
		</label>
				</c:if>


				</c:forEach>

			</div>
		</div>
		<br>

		<div class="row">
			<div class="col-md-12">
				<div style="margin-left: 100px;">
					<input type="submit" value="save" class="btn btn-default">
				</div>

			</div>
		</div>
	</form>
	<br>
	<table class="table table-bordered table-condensed table-hover">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Password</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${data}" var="a" varStatus="loop">

				<tr>
					<td>${loop.count}</td>
					<td>${a.name}</td>
					<td>${a.password}</td>
					<td><a href="/edit/${a.id}">
							<button>Edit</button>
					</a></td>
					<td><a href="/delete?id=${a.id}">
							<button>Delete</button>
					</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>


</div>
<div class="container">	

 <h1 class="my-4" style="color: green;" >Add Roles
      </h1>
      <hr style="border: 2px solid green" />
	<form action="/saveRole" method="post">
		<div class="row">
			<div class="col-md-12">
				name: &nbsp; &nbsp; &nbsp; &nbsp;<input type="text" name="name" value="${data1.name}">
				
				<c:if test="${! empty data1 }">
					<input type="hidden" name="id" value="${data1.id}">
				</c:if>
				
			</div>
		</div>
		<br>
		
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
				<th>Sl. No</th>
				<th>Name</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${roles}" var="a" varStatus="loop">

				<tr>
					<td>${loop.count}</td>
					<td>${a.name}</td>
					<td><a href="/editRole/${a.id}">
							<button>Edit</button>
					</a></td>
					<td><a href="/deleteRole?id=${a.id}">
							<button>Delete</button>
					</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>


</div>
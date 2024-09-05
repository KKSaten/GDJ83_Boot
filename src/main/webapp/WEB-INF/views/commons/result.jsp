<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>

	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6 mt-5">

				<form method="post">
					<div class="mb-3">
						<label for="username" class="form-label">ID</label>
						<input type="text" class="form-control" id="username" name="username">
					</div>
					
					<div class="mb-3">
						<label for="password" class="form-label">Password</label>
						<input type="password" class="form-control" id="password" name="password">
					</div>
					
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>

			</div>



		</div>

	</div>



	<script type="text/javascript">
		alert('${msg}')
		location.href="${path}";
	</script>
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="message" class="java.lang.String" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>School Safety App</title>
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" />
<link href="css/main-style.css" rel="stylesheet" />

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#title").load("title.txt");
	});
	$(function() {
		$("#menu").load("menu.list");
	});
</script>
</head>

<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation" id="navbar">
			<div class="navbar-header">
				<a href="./index.html"><font color="white" id="title"></font></a>
			</div>
		</nav>
		<div class="navbar-default navbar-static-side">
			<div class="sidebar-collapse">
				<div id="menu"></div>
			</div>
		</div>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Result</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Message</div>
						<div class="panel-body">
							<div class="table-responsive">
								<%=message%>
							</div>
						</div>
					</div>
					<!--End Tables01 -->
				</div>
			</div>
		</div>
		<!-- end page-wrapper -->
	</div>
	<!-- end wrapper -->
</body>

</html>
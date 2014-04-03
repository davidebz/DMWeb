<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/dmweb-fragment-taglib.tld" prefix="dmweb"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="leaflet-0.6.4/leaflet.js"></script>
<script src="leaflet-0.6.4/leaflet.label.js"></script>
<link rel="stylesheet" href="leaflet-0.6.4/leaflet.css" />
<!--[if lte IE 8]>
    <link rel="stylesheet" href="leaflet-0.6.4/leaflet.ie.css" />
<![endif]-->
<link rel="stylesheet" href="leaflet-0.6.4/leaflet.label.css" />

<script type="text/javascript" src="dmweb/dmweb.nocache.js"></script>

<link href="bootstrap-3.1.1-dist/css/bootstrap.min.css" rel="stylesheet">
<link href="dmweb-widgets-bootstrap.css" rel="stylesheet">

</head>
<dmweb:body marshallerUnmarshallerPrefix="bz.davide.dmweb.shared.view.example.LeafletMapExample">
	
	<%--
	<dmweb:fragment viewclass="bz.davide.dmweb.shared.view.example.SignInView">
		<initParameters> 
		</initParameters>
	</dmweb:fragment> 
	 --%>
	 
	 <div id="main"></div>
	 
	<!-- OPTIONAL: include this if you want history support -->
	<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1' style="position: absolute; width: 0; height: 0; border: 0"></iframe>
</dmweb:body>
</html>
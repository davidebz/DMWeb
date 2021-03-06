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
<link rel="stylesheet" href="DMWeb.css" />

</head>
<dmweb:body marshallerUnmarshallerPrefix="bz.davide.dmweb.shared.view.example.LeafletMapExample">
	Uncomment examples in the source code!
	
	<dmweb:fragment viewclass="bz.davide.dmweb.shared.view.example.LeafletMapExample">
		<initParameters> 
			<title>map with bus stops</title>
			<map>
				<width>400</width> 
				<height>300</height>
				<initialLat>46.05</initialLat>
				<initialLon>11</initialLon>
				<initialZoom>10</initialZoom>
			</map>
			<pois>
			   <name>S. Geltrude</name>
			   <lat>46</lat>
			   <lon>11</lon>
			</pois>
			<pois>
			   <name>S. Geltrude 2</name>
			   <lat>46.1</lat>
			   <lon>11.05</lon>
			</pois>
			<pois>
			   <name>Claudia A.</name>
			   <lat>46.03</lat>
			   <lon>11.02</lon>
			</pois>
		</initParameters>
	</dmweb:fragment>

	<!-- OPTIONAL: include this if you want history support -->
	<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1' style="position: absolute; width: 0; height: 0; border: 0"></iframe>
</dmweb:body>
</html>
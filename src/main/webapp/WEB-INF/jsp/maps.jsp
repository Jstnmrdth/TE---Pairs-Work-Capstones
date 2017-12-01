<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<style>
/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
#mapPage {
	height: 100%;
}
/* /* Optional: Makes the sample page fill the window. */
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}
*
/
</style>

<div class="jumbotron"
	style="background-image: url('img/homepage/homepage.jpg')">
	<section class="homePage container-fluid">
		<h2 id="title-text-homepage" class="center">
			<style>
@import url('https://fonts.googleapis.com/css?family=Work+Sans');
</style>
			Local Brews
		</h2>
		<div class="row">
			<div class="brewery col-sm-12 col-md-12">
				<div class="iframe-container">
					<div id="mapPage" class="img-rounded"></div>
				</div>
			</div>
		</div>
	</section>
</div>

<script>
function initMap() {
	var map = new google.maps.Map(document.getElementById('mapPage'), {
		zoom : 5,
		center : {
			lat : -28.024,
			lng : 140.887
		}
	});

	var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';

	var locations = [ {
		lat : -31.563910,
		lng : 147.154312
	}, {
		lat : -33.718234,
		lng : 150.363181
	}, {
		lat : -33.727111,
		lng : 150.371124
	}, {
		lat : -33.848588,
		lng : 151.209834
	}, {
		lat : -33.851702,
		lng : 151.216968
	}, {
		lat : -34.671264,
		lng : 150.863657
	}, {
		lat : -35.304724,
		lng : 148.662905
	}, {
		lat : -36.817685,
		lng : 175.699196
	}, {
		lat : -36.828611,
		lng : 175.790222
	}, {
		lat : -37.750000,
		lng : 145.116667
	}, {
		lat : -37.759859,
		lng : 145.128708
	}, {
		lat : -37.765015,
		lng : 145.133858
	}, {
		lat : -37.770104,
		lng : 145.143299
	}, {
		lat : -37.773700,
		lng : 145.145187
	}, {
		lat : -37.774785,
		lng : 145.137978
	}, {
		lat : -37.819616,
		lng : 144.968119
	}, {
		lat : -38.330766,
		lng : 144.695692
	}, {
		lat : -39.927193,
		lng : 175.053218
	}, {
		lat : -41.330162,
		lng : 174.865694
	}, {
		lat : -42.734358,
		lng : 147.439506
	}, {
		lat : -42.734358,
		lng : 147.501315
	}, {
		lat : -42.735258,
		lng : 147.438000
	}, {
		lat : -43.999792,
		lng : 170.463352
	} ];
	
	var markers = locations.map(function(location, i) {
		return new google.maps.Marker({
			position : location,
			label : labels[i % labels.length]
		});
	});

	var markerCluster = new MarkerClusterer(
			map,
			markers,
			{
				<c:url var="imageLoc" value="/google-maps-resources/m"/>
				imagePath : '${imageLoc}'
			});
}
	  
</script>
<c:url var="clusterLoc" value="/google-maps-resources/markerclusterer.js"/>
<script src="${clusterLoc}"></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBQJt8sM0_cDCBf16mHs46XUmxHJg22JHY&callback=initMap"></script>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />
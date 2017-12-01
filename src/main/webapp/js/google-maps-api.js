function initMap() {
        var location = {lat: 39.958, lng: -83.0114};
//		var latitude = '${landGrant.latitude}';
//		var location = {lat: latitude, lng: -83.0114};
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 14,
          center: location
        });
        var marker = new google.maps.Marker({
          position: location,
          map: map
        });
      }
	protected boolean testTypeAndVertexEquality(Geometry geom1, Geometry geom2) {
		if ( !geom1.getGeometryType().equals( geom2.getGeometryType() ) ) {
			return false;
		}
		if ( geom1.getNumGeometries() != geom2.getNumGeometries() ) {
			return false;
		}
		if ( geom1.getNumPoints() != geom2.getNumPoints() ) {
			return false;
		}
		Coordinate[] coordinates1 = geom1.getCoordinates();
		Coordinate[] coordinates2 = geom2.getCoordinates();
		for ( int i = 0; i < coordinates1.length; i++ ) {
			Coordinate c1 = coordinates1[i];
			Coordinate c2 = coordinates2[i];
			if ( !testCoordinateEquality( c1, c2 ) ) {
				return false;
			}
		}
		return true;
	}

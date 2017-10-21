	private boolean testVerticesEquality(Geometry geom1, Geometry geom2) {
		if ( geom1.getNumPoints() != geom2.getNumPoints() ) {
			return false;
		}
		for ( int i = 0; i < geom1.getNumPoints(); i++ ) {
			Coordinate cn1 = geom1.getCoordinates()[i];
			Coordinate cn2 = geom2.getCoordinates()[i];
			if ( !cn1.equals2D( cn2 ) ) {
				return false;
			}
		}
		return true;
	}

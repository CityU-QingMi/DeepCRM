	private boolean testCoordinateEquality(Coordinate c1, Coordinate c2) {
//		if ( c1 instanceof MCoordinate ) {
//			if ( !( c2 instanceof MCoordinate ) ) {
//				return false;
//			}
//			MCoordinate mc1 = (MCoordinate) c1;
//			MCoordinate mc2 = (MCoordinate) c2;
//			if ( !Double.isNaN( mc1.m ) && mc1.m != mc2.m ) {
//				return false;
//			}
//		}
		if ( !Double.isNaN( c1.z ) && c1.z != c2.z ) {
			return false;
		}
		return c1.x == c2.x && c1.y == c2.y;
	}

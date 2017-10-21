	private boolean test(Geometry geom1, Geometry geom2, boolean ignoreSRID) {
		if ( geom1 == null ) {
			return geom2 == null;
		}
		if ( geom1.isEmpty() ) {
			return geom2.isEmpty();
		}
		if ( !ignoreSRID && !equalSRID( geom1, geom2 ) ) {
			return false;
		}

		if ( geom1 instanceof GeometryCollection ) {
			if ( !(geom2 instanceof GeometryCollection) ) {
				return false;
			}
			GeometryCollection expectedCollection = (GeometryCollection) geom1;
			GeometryCollection receivedCollection = (GeometryCollection) geom2;
			for ( int partIndex = 0; partIndex < expectedCollection.getNumGeometries(); partIndex++ ) {
				Geometry partExpected = expectedCollection.getGeometryN( partIndex );
				Geometry partReceived = receivedCollection.getGeometryN( partIndex );
				if ( !test( partExpected, partReceived, true ) ) {
					return false;
				}
			}
			return true;
		}
		else {
			return testSimpleGeometryEquality( geom1, geom2 );
		}
	}

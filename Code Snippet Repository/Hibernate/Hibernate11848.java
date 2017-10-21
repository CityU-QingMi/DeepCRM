	public static Criterion spatialRestriction(int relation, String propertyName, Geometry value) {
		switch ( relation ) {
			case SpatialRelation.CONTAINS:
				return contains( propertyName, value );
			case SpatialRelation.CROSSES:
				return crosses( propertyName, value );
			case SpatialRelation.DISJOINT:
				return disjoint( propertyName, value );
			case SpatialRelation.INTERSECTS:
				return intersects( propertyName, value );
			case SpatialRelation.EQUALS:
				return eq( propertyName, value );
			case SpatialRelation.FILTER:
				return filter( propertyName, value );
			case SpatialRelation.OVERLAPS:
				return overlaps( propertyName, value );
			case SpatialRelation.TOUCHES:
				return touches( propertyName, value );
			case SpatialRelation.WITHIN:
				return within( propertyName, value );
			default:
				throw new IllegalArgumentException(
						"Non-existant spatial relation passed."
				);
		}
	}

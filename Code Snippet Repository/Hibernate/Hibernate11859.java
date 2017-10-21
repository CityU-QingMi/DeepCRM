	@Override
	public String getSpatialRelateSQL(String columnName, int spatialRelation) {
		switch ( spatialRelation ) {
			case SpatialRelation.WITHIN:
				return columnName + ".ST_Within(ST_GeomFromEWKB(?)) = 1";
			case SpatialRelation.CONTAINS:
				return columnName + ".ST_Contains(ST_GeomFromEWKB(?)) = 1";
			case SpatialRelation.CROSSES:
				return columnName + ".ST_Crosses(ST_GeomFromEWKB(?)) = 1";
			case SpatialRelation.OVERLAPS:
				return columnName + ".ST_Overlaps(ST_GeomFromEWKB(?)) = 1";
			case SpatialRelation.DISJOINT:
				return columnName + ".ST_Disjoint(ST_GeomFromEWKB(?)) = 1";
			case SpatialRelation.INTERSECTS:
				return columnName + ".ST_Intersects(ST_GeomFromEWKB(?)) = 1";
			case SpatialRelation.TOUCHES:
				return columnName + ".ST_Touches(ST_GeomFromEWKB(?)) = 1";
			case SpatialRelation.EQUALS:
				return columnName + ".ST_Equals(ST_GeomFromEWKB(?)) = 1";
			case SpatialRelation.FILTER:
				return columnName + ".ST_IntersectsFilter(ST_GeomFromEWKB(?)) = 1";
			default:
				throw new IllegalArgumentException( "Spatial relation is not known by this dialect" );
		}
	}

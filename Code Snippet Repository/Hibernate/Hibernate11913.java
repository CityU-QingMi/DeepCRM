	@Override
	public String getSpatialRelateSQL(String columnName, int spatialRelation) {
		final String stfunction;
		switch ( spatialRelation ) {
			case SpatialRelation.WITHIN:
				stfunction = "STWithin";
				break;
			case SpatialRelation.CONTAINS:
				stfunction = "STContains";
				break;
			case SpatialRelation.CROSSES:
				stfunction = "STCrosses";
				break;
			case SpatialRelation.OVERLAPS:
				stfunction = "STOverlaps";
				break;
			case SpatialRelation.DISJOINT:
				stfunction = "STDisjoint";
				break;
			case SpatialRelation.INTERSECTS:
				stfunction = "STIntersects";
				break;
			case SpatialRelation.TOUCHES:
				stfunction = "STTouches";
				break;
			case SpatialRelation.EQUALS:
				stfunction = "STEquals";
				break;
			default:
				throw new IllegalArgumentException(
						"Spatial relation is not known by this dialect"
				);
		}

		return columnName + "." + stfunction + "(?) = 1";
	}

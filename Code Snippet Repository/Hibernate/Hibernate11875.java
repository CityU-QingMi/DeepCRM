	String getNativeSpatialRelateSQL(String arg1, String arg2, int spatialRelation) {
		String mask;
		boolean negate = false;
		switch ( spatialRelation ) {
			case SpatialRelation.INTERSECTS:
				mask = "ANYINTERACT";
				break;
			case SpatialRelation.CONTAINS:
				mask = "CONTAINS+COVERS";
				break;
			case SpatialRelation.CROSSES:
				throw new UnsupportedOperationException(
						"Oracle Spatial does't have equivalent CROSSES relationship"
				);
			case SpatialRelation.DISJOINT:
				mask = "ANYINTERACT";
				negate = true;
				break;
			case SpatialRelation.EQUALS:
				mask = "EQUAL";
				break;
			case SpatialRelation.OVERLAPS:
				mask = "OVERLAPBDYDISJOINT+OVERLAPBDYINTERSECT";
				break;
			case SpatialRelation.TOUCHES:
				mask = "TOUCH";
				break;
			case SpatialRelation.WITHIN:
				mask = "INSIDE+COVEREDBY";
				break;
			default:
				throw new IllegalArgumentException(
						"undefined SpatialRelation passed (" + spatialRelation
								+ ")"
				);
		}
		final StringBuilder buffer = new StringBuilder( "CASE SDO_RELATE(" ).append( arg1 )
				.append( "," )
				.append( arg2 )
				.append( ",'mask=" )
				.append( mask )
				.append( "') " );
		if ( !negate ) {
			buffer.append( " WHEN 'TRUE' THEN 1 ELSE 0 END" );
		}
		else {
			buffer.append( " WHEN 'TRUE' THEN 0 ELSE 1 END" );
		}
		return buffer.toString();
	}

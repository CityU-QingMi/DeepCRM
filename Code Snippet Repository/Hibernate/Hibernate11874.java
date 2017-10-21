	public String getOGCSpatialRelateSQL(String arg1, String arg2, int spatialRelation) {
		final StringBuffer ogcFunction = new StringBuffer( "MDSYS." );
		switch ( spatialRelation ) {
			case SpatialRelation.INTERSECTS:
				ogcFunction.append( "OGC_INTERSECTS" );
				break;
			case SpatialRelation.CONTAINS:
				ogcFunction.append( "OGC_CONTAINS" );
				break;
			case SpatialRelation.CROSSES:
				ogcFunction.append( "OGC_CROSS" );
				break;
			case SpatialRelation.DISJOINT:
				ogcFunction.append( "OGC_DISJOINT" );
				break;
			case SpatialRelation.EQUALS:
				ogcFunction.append( "OGC_EQUALS" );
				break;
			case SpatialRelation.OVERLAPS:
				ogcFunction.append( "OGC_OVERLAP" );
				break;
			case SpatialRelation.TOUCHES:
				ogcFunction.append( "OGC_TOUCH" );
				break;
			case SpatialRelation.WITHIN:
				ogcFunction.append( "OGC_WITHIN" );
				break;
			default:
				throw new IllegalArgumentException(
						"Unknown SpatialRelation ("
								+ spatialRelation + ")."
				);
		}
		ogcFunction.append( "(" ).append( "MDSYS.ST_GEOMETRY.FROM_SDO_GEOM(" )
				.append( arg1 ).append( ")," ).append(
				"MDSYS.ST_GEOMETRY.FROM_SDO_GEOM("
		).append( arg2 )
				.append( ")" ).append( ")" );
		return ogcFunction.toString();

	}

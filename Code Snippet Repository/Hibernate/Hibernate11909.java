	@Override
	public String getSpatialAggregateSQL(String columnName, int aggregation) {
		switch ( aggregation ) {
			case SpatialAggregate.EXTENT:
				final StringBuilder stbuf = new StringBuilder();
				stbuf.append( "st_extent(" ).append( columnName ).append( ")::geometry" );
				return stbuf.toString();
			default:
				throw new IllegalArgumentException(
						"Aggregation of type "
								+ aggregation + " are not supported by this dialect"
				);
		}
	}

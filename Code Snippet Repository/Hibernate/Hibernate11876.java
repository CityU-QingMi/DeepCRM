	@Override
	public String getSpatialAggregateSQL(String columnName, int aggregation) {
		final StringBuffer aggregateFunction = new StringBuffer();
		final SpatialAggregate sa = new SpatialAggregate( aggregation );

		if ( sa.getAggregateSyntax() == null ) {
			throw new IllegalArgumentException(
					"Unknown Spatial Aggregation ("
							+ aggregation + ")."
			);
		}

		aggregateFunction.append( sa.getAggregateSyntax() );

		aggregateFunction.append( "(" );
		if ( sa.isAggregateType() ) {
			aggregateFunction.append( "SDOAGGRTYPE(" );
		}
		aggregateFunction.append( columnName );
		// TODO tolerance must by configurable
		if ( sa.isAggregateType() ) {
			aggregateFunction.append( ", " ).append( .001 ).append( ")" );
		}
		aggregateFunction.append( ")" );

		return aggregateFunction.toString();
	}

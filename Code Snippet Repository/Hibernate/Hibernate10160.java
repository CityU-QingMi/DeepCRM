		@Override
		public void appendJoin(boolean firstFromElement, StringBuilder builder, Map<String, Object> queryParamValues) {
			if (firstFromElement) {
				throw new IllegalArgumentException( "An inner/outer join cannot come as first 'from element'" );
			}
			builder.append( ' ' ).append( joinType.name()
					.toLowerCase( Locale.US ) ).append( " join " )
					.append( entityName ).append( ' ' )
					.append( getAlias() ).append( " on " );
			joinConditionParameters.build( builder, queryParamValues );
		}

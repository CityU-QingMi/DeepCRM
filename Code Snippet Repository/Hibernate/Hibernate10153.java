	void build(StringBuilder sb, Map<String, Object> queryParamValues) {
		final MutableBoolean isFirst = new MutableBoolean( true );

		for ( String expression : expressions ) {
			append( sb, expression, isFirst );
		}

		for ( Parameters sub : subParameters ) {
			if ( !subParameters.isEmpty() ) {
				append( sb, "(", isFirst );
				sub.build( sb, queryParamValues );
				sb.append( ")" );
			}
		}

		for ( Parameters negated : negatedParameters ) {
			if ( !negatedParameters.isEmpty() ) {
				append( sb, "not (", isFirst );
				negated.build( sb, queryParamValues );
				sb.append( ")" );
			}
		}

		queryParamValues.putAll( localQueryParamValues );
	}

		private String renderCountDistinct(List arguments, Dialect dialect) {
			final StringBuilder buffer = new StringBuilder();
			buffer.append( "count(distinct " );
			if (dialect.requiresParensForTupleDistinctCounts()) {
				buffer.append("(");
			}
			String sep = "";
			final Iterator itr = arguments.iterator();
			// intentionally skip first
			itr.next();
			while ( itr.hasNext() ) {
				buffer.append( sep ).append( itr.next() );
				sep = ", ";
			}
			if (dialect.requiresParensForTupleDistinctCounts()) {
				buffer.append(")");
			}
			return buffer.append( ")" ).toString();
		}

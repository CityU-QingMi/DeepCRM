	public static String render(PredicateImplementor predicate, RenderingContext renderingContext) {
		if ( ! predicate.isJunction() ) {
			throw new IllegalStateException( "CompoundPredicate.render should only be used to render junctions" );
		}

		// for junctions, the negation is already cooked into the expressions and operator; we just need to render
		// them as is

		if ( predicate.getExpressions().isEmpty() ) {
			boolean implicitTrue = predicate.getOperator() == BooleanOperator.AND;
			// AND is always true for empty; OR is always false
			return implicitTrue ? "1=1" : "0=1";
		}

		// single valued junction ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		if ( predicate.getExpressions().size() == 1 ) {
			return ( (Renderable) predicate.getExpressions().get( 0 ) ).render( renderingContext );
		}

		final StringBuilder buffer = new StringBuilder();
		String sep = "";
		for ( Expression expression : predicate.getExpressions() ) {
			buffer.append( sep )
					.append( "( " )
					.append( ( (Renderable) expression ).render( renderingContext ) )
					.append( " )" );
			sep = operatorTextWithSeparator( predicate.getOperator() );
		}
		return buffer.toString();
	}

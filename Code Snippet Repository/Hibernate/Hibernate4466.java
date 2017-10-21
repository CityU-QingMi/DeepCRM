	@Override
	public String render(boolean isNegated, RenderingContext renderingContext) {
		final StringBuilder buffer = new StringBuilder();
		final Expression exp = getExpression();
		if ( ParameterExpressionImpl.class.isInstance( exp ) ) {
			// technically we only need to CAST (afaik) if expression and all values are parameters.
			// but checking for that condition could take long time on a lon value list
			final ParameterExpressionImpl parameterExpression = (ParameterExpressionImpl) exp;
			final SessionFactoryImplementor sfi = criteriaBuilder().getEntityManagerFactory().unwrap( SessionFactoryImplementor.class );
			final Type mappingType = sfi.getTypeResolver().heuristicType( parameterExpression.getParameterType().getName() );
			buffer.append( "cast(" )
					.append( parameterExpression.render( renderingContext ) )
					.append( " as " )
					.append( mappingType.getName() )
					.append( ")" );
		}
		else {
			buffer.append( ( (Renderable) getExpression() ).render( renderingContext ) );
		}

		if ( isNegated ) {
			buffer.append( " not" );
		}
		buffer.append( " in " );

		// subquery expressions are already wrapped in parenthesis, so we only need to
		// render the parenthesis here if the values represent an explicit value list
		boolean isInSubqueryPredicate = getValues().size() == 1
				&& Subquery.class.isInstance( getValues().get( 0 ) );
		if ( isInSubqueryPredicate ) {
			buffer.append( ( (Renderable) getValues().get(0) ).render( renderingContext ) );
		}
		else {
			buffer.append( '(' );
			String sep = "";
			for ( Expression value : getValues() ) {
				buffer.append( sep )
						.append( ( (Renderable) value ).render( renderingContext ) );
				sep = ", ";
			}
			buffer.append( ')' );
		}
		return buffer.toString();
	}

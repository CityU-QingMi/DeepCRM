	private String render(
			RenderingContext renderingContext,
			BiFunction<Renderable, RenderingContext, String> formatter) {
		StringBuilder caseExpr = new StringBuilder();
		caseExpr.append( "case " )
				.append( formatter.apply( (Renderable) getExpression(), renderingContext ) );
		for ( WhenClause whenClause : getWhenClauses() ) {
			caseExpr.append( " when " )
					.append( formatter.apply( whenClause.getCondition(), renderingContext ) )
					.append( " then " )
					.append( formatter.apply( (Renderable) whenClause.getResult(), renderingContext ) );
		}
		caseExpr.append( " else " )
				.append( formatter.apply( (Renderable) getOtherwiseResult(), renderingContext ) )
				.append( " end" );
		return caseExpr.toString();
	}

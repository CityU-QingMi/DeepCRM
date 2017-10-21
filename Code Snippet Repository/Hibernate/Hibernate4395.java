	private String render(
			RenderingContext renderingContext,
			BiFunction<Renderable, RenderingContext, String> formatter) {
		StringBuilder caseStatement = new StringBuilder( "case" );
		for ( WhenClause whenClause : getWhenClauses() ) {
			caseStatement.append( " when " )
					.append( formatter.apply( (Renderable) whenClause.getCondition(), renderingContext ) )
					.append( " then " )
					.append( formatter.apply( ((Renderable) whenClause.getResult()), renderingContext ) );
		}
		caseStatement.append( " else " )
				.append( formatter.apply( (Renderable) getOtherwiseResult(), renderingContext ) )
				.append( " end" );
		return caseStatement.toString();
	}

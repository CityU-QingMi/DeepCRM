	@Override
	public String renderTableExpression(RenderingContext renderingContext) {
		prepareAlias( renderingContext );
		( (FromImplementor) getParent() ).prepareAlias( renderingContext );
		StringBuilder tableExpression = new StringBuilder();
		tableExpression.append( getParent().getAlias() )
				.append( '.' )
				.append( getAttribute().getName() )
				.append( " as " )
				.append( getAlias() );
		if ( suppliedJoinCondition != null ) {
			tableExpression.append( " with " )
					.append( ( (AbstractPredicateImpl) suppliedJoinCondition ).render( renderingContext ) );
		}
		return tableExpression.toString();
	}

	@Override
	public String getRenderText(SessionFactoryImplementor sessionFactory) {
		int scalarColumnIndex = selectExpression.getScalarColumnIndex();
		if ( scalarColumnIndex < 0 ) {
			throw new IllegalStateException(
					"selectExpression.getScalarColumnIndex() must be >= 0; actual = " + scalarColumnIndex
			);
		}
		return sessionFactory.getDialect().replaceResultVariableInOrderByClauseWithPosition() ?
			getColumnPositionsString( scalarColumnIndex ) :
			getColumnNamesString( scalarColumnIndex );

	}

	public void processQuerySpaceJoins(QuerySpace querySpace, SelectStatementBuilder selectStatementBuilder) {
		LOG.debug( "processing queryspace " + querySpace.getUid() );
		final JoinFragment joinFragment = factory.getDialect().createOuterJoinFragment();
		processQuerySpaceJoins( querySpace, joinFragment );

		selectStatementBuilder.setOuterJoins(
				joinFragment.toFromFragmentString(),
				joinFragment.toWhereFragmentString()
		);
	}

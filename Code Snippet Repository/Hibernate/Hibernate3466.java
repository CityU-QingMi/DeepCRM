	private void initStatementString(
			final String projection,
			final String condition,
			final String orderBy,
			final String groupBy,
			final LockOptions lockOptions) throws MappingException {

		final int joins = countEntityPersisters( associations );
		suffixes = BasicLoader.generateSuffixes( joins + 1 );

		JoinFragment ojf = mergeOuterJoins( associations );

		Select select = new Select( getDialect() )
				.setLockOptions( lockOptions )
				.setSelectClause(
						projection == null ?
								persister.selectFragment( alias, suffixes[joins] ) + selectString( associations ) :
								projection
				)
				.setFromClause(
						getDialect().appendLockHint( lockOptions, persister.fromTableFragment( alias ) ) +
								persister.fromJoinFragment( alias, true, true )
				)
				.setWhereClause( condition )
				.setOuterJoins(
						ojf.toFromFragmentString(),
						ojf.toWhereFragmentString() + getWhereFragment()
				)
				.setOrderByClause( orderBy( associations, orderBy ) )
				.setGroupByClause( groupBy );

		if ( getFactory().getSessionFactoryOptions().isCommentsEnabled() ) {
			select.setComment( getComment() );
		}
		sql = select.toStatementString();
	}

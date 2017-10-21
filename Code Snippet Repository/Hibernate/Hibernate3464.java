	protected final void initAll(
			final String whereString,
			final String orderByString,
			final LockOptions lockOptions,
			final AssociationInitCallback callback) throws MappingException {
		walkEntityTree( persister, getAlias() );
		List allAssociations = new ArrayList();
		allAssociations.addAll( associations );
		allAssociations.add( OuterJoinableAssociation.createRoot( persister.getEntityType(), alias, getFactory() ) );
		initPersisters( allAssociations, lockOptions, callback );
		initStatementString( whereString, orderByString, lockOptions );
	}

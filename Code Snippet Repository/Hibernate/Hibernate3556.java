	public OneToManyJoinWalker(
			QueryableCollection oneToManyPersister,
			int batchSize,
			String subquery,
			SessionFactoryImplementor factory,
			LoadQueryInfluencers loadQueryInfluencers) throws MappingException {
		super( factory, loadQueryInfluencers );

		this.oneToManyPersister = oneToManyPersister;

		final OuterJoinLoadable elementPersister = (OuterJoinLoadable) oneToManyPersister.getElementPersister();
		final String alias = generateRootAlias( oneToManyPersister.getRole() );

		walkEntityTree( elementPersister, alias );

		List allAssociations = new ArrayList();
		allAssociations.addAll( associations );
		allAssociations.add(
				OuterJoinableAssociation.createRoot(
						oneToManyPersister.getCollectionType(),
						alias,
						getFactory()
				)
		);
		initPersisters( allAssociations, LockMode.NONE );
		initStatementString( elementPersister, alias, batchSize, subquery );
	}

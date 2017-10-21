	public BasicCollectionJoinWalker(
			QueryableCollection collectionPersister, 
			int batchSize, 
			String subquery, 
			SessionFactoryImplementor factory, 
			LoadQueryInfluencers loadQueryInfluencers) throws MappingException {

		super( factory, loadQueryInfluencers );

		this.collectionPersister = collectionPersister;

		String alias = generateRootAlias( collectionPersister.getRole() );

		walkCollectionTree(collectionPersister, alias);

		List allAssociations = new ArrayList();
		allAssociations.addAll(associations);
		allAssociations.add( OuterJoinableAssociation.createRoot( collectionPersister.getCollectionType(), alias, getFactory() ) );
		initPersisters(allAssociations, LockMode.NONE);
		initStatementString(alias, batchSize, subquery);
	}

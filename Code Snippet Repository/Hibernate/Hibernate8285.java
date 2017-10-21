	@Override
	protected void prepareTest() throws Exception {
		super.prepareTest();
		isPlanContractsInverse = sessionFactory().getCollectionPersister( Plan.class.getName() + ".contracts" ).isInverse();
		try {
			sessionFactory().getCollectionPersister( Contract.class.getName() + ".plans" );
			isPlanContractsBidirectional = true;
		}
		catch ( MappingException ex) {
			isPlanContractsBidirectional = false;	
		}
		isPlanVersioned = sessionFactory().getEntityPersister( Plan.class.getName() ).isVersioned();
		isContractVersioned = sessionFactory().getEntityPersister( Contract.class.getName() ).isVersioned();
		sessionFactory().getStatistics().clear();
	}

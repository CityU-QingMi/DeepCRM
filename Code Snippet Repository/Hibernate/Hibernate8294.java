	protected void prepareTest() throws Exception {
		super.prepareTest();
		isContractPartiesInverse = sessionFactory().getCollectionPersister( Contract.class.getName() + ".parties" ).isInverse();
		try {
			 sessionFactory().getEntityPersister( Party.class.getName() ).getPropertyType( "contract" );
			isContractPartiesBidirectional = true;
		}
		catch ( QueryException ex) {
			isContractPartiesBidirectional = false;
		}
		try {
			 sessionFactory().getEntityPersister( ContractVariation.class.getName() ).getPropertyType( "contract" );
			isContractVariationsBidirectional = true;
		}
		catch ( QueryException ex) {
			isContractVariationsBidirectional = false;
		}

		isContractVersioned = sessionFactory().getEntityPersister( Contract.class.getName() ).isVersioned();
	}

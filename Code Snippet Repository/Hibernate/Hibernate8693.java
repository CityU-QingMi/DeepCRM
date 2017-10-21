	@Test
	public void testManyToMany() {
		Configuration cfg = new Configuration();
		cfg.addResource( "org/hibernate/test/immutable/entitywithmutablecollection/inverse/ContractVariation.hbm.xml" );
		SessionFactoryImplementor sf = (SessionFactoryImplementor) cfg.buildSessionFactory();

		try {
			doCompare( sf, (OuterJoinLoadable) sf.getClassMetadata( org.hibernate.test.immutable.entitywithmutablecollection.Contract.class ) );
		}
		finally {
			sf.close();
		}

	}

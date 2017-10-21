	@Test
	public void testSpecialOneToOne() {
		// tests the mappings defined in org.hibernate.test.onetoone.joined.JoinedSubclassOneToOneTest
		Configuration cfg = new Configuration();
		cfg.addResource( "org/hibernate/test/onetoone/formula/Person.hbm.xml" );
		SessionFactoryImplementor sf = (SessionFactoryImplementor) cfg.buildSessionFactory();

		try {
			doCompare( sf, (OuterJoinLoadable) sf.getClassMetadata( org.hibernate.test.onetoone.formula.Person.class ) );
		}
		finally {
			sf.close();
		}
	}

	@Test
	public void testEncapsulatedCompositeIdNoFetches2() {
		// Parent is an entity with a composite identifier mapped via a @EmbeddedId class (ParentPK) which is defined
		// using just basic types (strings, ints, etc)
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass( EncapsulatedCompositeIdResultSetProcessorTest.Parent.class );
		SessionFactoryImplementor sf = (SessionFactoryImplementor) cfg.buildSessionFactory();

		try {
			doCompare( sf, (OuterJoinLoadable) sf.getClassMetadata( EncapsulatedCompositeIdResultSetProcessorTest.Parent.class ) );
		}
		finally {
			sf.close();
		}
	}

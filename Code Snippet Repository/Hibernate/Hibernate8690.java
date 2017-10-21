	@Test
	public void testEncapsulatedCompositeIdNoFetches1() {
		// CardField is an entity with a composite identifier mapped via a @EmbeddedId class (CardFieldPK) defining
		// a @ManyToOne
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass( EncapsulatedCompositeIdResultSetProcessorTest.CardField.class );
		cfg.addAnnotatedClass( EncapsulatedCompositeIdResultSetProcessorTest.Card.class );
		SessionFactoryImplementor sf = (SessionFactoryImplementor) cfg.buildSessionFactory();

		try {
			doCompare( sf, (OuterJoinLoadable) sf.getClassMetadata( EncapsulatedCompositeIdResultSetProcessorTest.CardField.class ) );
			doCompare( sf, (OuterJoinLoadable) sf.getClassMetadata( EncapsulatedCompositeIdResultSetProcessorTest.Card.class ) );
		}
		finally {
			sf.close();
		}
	}

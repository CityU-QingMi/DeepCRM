	@Test
	public void testOracleSequenceOrder() {
		EntityPersister persister = sessionFactory().getEntityPersister( SequenceIdRevisionEntity.class.getName() );
		IdentifierGenerator generator = persister.getIdentifierGenerator();
		Assert.assertTrue( OrderedSequenceGenerator.class.isInstance( generator ) );
		OrderedSequenceGenerator seqGenerator = (OrderedSequenceGenerator) generator;
		Assert.assertTrue(
				"Oracle sequence needs to be ordered in RAC environment.",
				seqGenerator.sqlCreateStrings( getDialect() )[0].toLowerCase().endsWith( " order" )
		);
	}

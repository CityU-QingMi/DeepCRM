	@Test
	public void testMinimalSequenceEntity() {
		final EntityPersister persister = sessionFactory().getEntityPersister( MinimalSequenceEntity.class.getName() );
		IdentifierGenerator generator = persister.getIdentifierGenerator();
		assertTrue( SequenceStyleGenerator.class.isInstance( generator ) );
		SequenceStyleGenerator seqGenerator = (SequenceStyleGenerator) generator;
		assertEquals( MinimalSequenceEntity.SEQ_NAME, seqGenerator.getDatabaseStructure().getName() );
		// 1 is the annotation default
		assertEquals( 1, seqGenerator.getDatabaseStructure().getInitialValue() );
		// 50 is the annotation default
		assertEquals( 50, seqGenerator.getDatabaseStructure().getIncrementSize() );
		assertFalse( NoopOptimizer.class.isInstance( seqGenerator.getOptimizer() ) );
	}

	@Test
	@TestForIssue(jiraKey = "")
	public void testSequencePerEntity() {
		// Checking first entity.
		EntityPersister persister = sessionFactory().getEntityPersister( DedicatedSequenceEntity1.class.getName() );
		IdentifierGenerator generator = persister.getIdentifierGenerator();
		assertTrue( SequenceStyleGenerator.class.isInstance( generator ) );
		SequenceStyleGenerator seqGenerator = (SequenceStyleGenerator) generator;
		assertEquals(
				StringHelper.unqualifyEntityName( DedicatedSequenceEntity1.class.getName() ) + DedicatedSequenceEntity1.SEQUENCE_SUFFIX,
				seqGenerator.getDatabaseStructure().getName()
		);

		// Checking second entity.
		persister = sessionFactory().getEntityPersister( DedicatedSequenceEntity2.class.getName() );
		generator = persister.getIdentifierGenerator();
		assertTrue( SequenceStyleGenerator.class.isInstance( generator ) );
		seqGenerator = (SequenceStyleGenerator) generator;
		assertEquals(
				DedicatedSequenceEntity2.ENTITY_NAME + DedicatedSequenceEntity1.SEQUENCE_SUFFIX,
				seqGenerator.getDatabaseStructure().getName()
		);
	}

	@Test
	@TestForIssue(jiraKey = "")
	public void testSequencePerEntity() {
		final String overriddenEntityName = "SpecialEntity";
		EntityPersister persister = sessionFactory().getEntityPersister( overriddenEntityName );
		assertClassAssignability( SequenceStyleGenerator.class, persister.getIdentifierGenerator().getClass() );
		SequenceStyleGenerator generator = (SequenceStyleGenerator) persister.getIdentifierGenerator();
		assertEquals( overriddenEntityName + SequenceStyleGenerator.DEF_SEQUENCE_SUFFIX, generator.getDatabaseStructure().getName() );

		Session s = openSession();
		s.beginTransaction();
		Entity entity1 = new Entity( "1" );
		s.save( overriddenEntityName, entity1 );
		Entity entity2 = new Entity( "2" );
		s.save( overriddenEntityName, entity2 );
		s.getTransaction().commit();

		assertEquals( 1, entity1.getId().intValue() );
		assertEquals( 2, entity2.getId().intValue() );
	}

	@Test
	public void testHibernateSequenceSchema() {
		EntityPersister persister = sessionFactory().getEntityPersister( HibernateSequenceEntity.class.getName() );
		IdentifierGenerator generator = persister.getIdentifierGenerator();
		Assert.assertTrue( SequenceStyleGenerator.class.isInstance( generator ) );
		SequenceStyleGenerator seqGenerator = (SequenceStyleGenerator) generator;
		Assert.assertEquals(
				Table.qualify( null, SCHEMA_NAME, SequenceStyleGenerator.DEF_SEQUENCE_NAME ),
				seqGenerator.getDatabaseStructure().getName()
		);
	}

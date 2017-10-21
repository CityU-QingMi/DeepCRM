	@Test
	@TestForIssue( jiraKey = "")
	public void testNaturalIdNullability() {
		final EntityPersister persister = sessionFactory().getEntityPersister( Building.class.getName() );
		final EntityMetamodel entityMetamodel = persister.getEntityMetamodel();
		// nullability is not specified, so they should be nullable by annotations-specific default
		assertTrue( persister.getPropertyNullability()[entityMetamodel.getPropertyIndex( "address" )] );
		assertTrue( persister.getPropertyNullability()[entityMetamodel.getPropertyIndex( "city" )] );
		assertTrue( persister.getPropertyNullability()[entityMetamodel.getPropertyIndex( "state" )] );
	}

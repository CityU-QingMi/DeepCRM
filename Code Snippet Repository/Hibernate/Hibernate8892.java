	@Test
	@TestForIssue( jiraKey = "")
	public void testNaturalIdNullability() {
		// A, B, C, and D are mapped using annotations;
		// none are mapped to be non-nullable, so all are nullable by annotations-specific default,
		// except primitives
		EntityPersister persister = sessionFactory().getEntityPersister( A.class.getName() );
		EntityMetamodel entityMetamodel = persister.getEntityMetamodel();
		assertTrue( persister.getPropertyNullability()[entityMetamodel.getPropertyIndex( "assC" )] );
		assertTrue( persister.getPropertyNullability()[entityMetamodel.getPropertyIndex( "myname" )] );

		persister = sessionFactory().getEntityPersister( B.class.getName() );
		entityMetamodel = persister.getEntityMetamodel();
		assertTrue( persister.getPropertyNullability()[entityMetamodel.getPropertyIndex( "assA" )] );
		// naturalid is a primitive, so it is non-nullable
		assertFalse( persister.getPropertyNullability()[entityMetamodel.getPropertyIndex( "naturalid" )] );

		persister = sessionFactory().getEntityPersister( C.class.getName() );
		entityMetamodel = persister.getEntityMetamodel();
		assertTrue( persister.getPropertyNullability()[entityMetamodel.getPropertyIndex( "name" )] );

		persister = sessionFactory().getEntityPersister( D.class.getName() );
		entityMetamodel = persister.getEntityMetamodel();
		assertTrue( persister.getPropertyNullability()[entityMetamodel.getPropertyIndex( "name" )] );
		assertTrue( persister.getPropertyNullability()[entityMetamodel.getPropertyIndex( "associatedC" )] );

		// User is mapped using hbm.xml; properties are explicitly mapped to be nullable
		persister = sessionFactory().getEntityPersister( User.class.getName() );
		entityMetamodel = persister.getEntityMetamodel();
		assertTrue( persister.getPropertyNullability()[entityMetamodel.getPropertyIndex( "name" )] );
		assertTrue( persister.getPropertyNullability()[entityMetamodel.getPropertyIndex( "org" )] );
		// intVal is a primitive; hbm.xml apparently allows primitive to be nullable
		assertTrue( persister.getPropertyNullability()[entityMetamodel.getPropertyIndex( "intVal" )] );
	}

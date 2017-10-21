	@Test
	@TestForIssue( jiraKey = "" )
	@SuppressWarnings("")
	public void testAttributeAccess() {
		final EntityType<SomeMappedSuperclassSubclass> entityType =  entityManagerFactory().getMetamodel().entity( SomeMappedSuperclassSubclass.class );
		final IdentifiableType<SomeMappedSuperclass> mappedSuperclassType = (IdentifiableType<SomeMappedSuperclass>) entityType.getSupertype();

		assertNotNull( entityType.getId( Long.class ) );
		try {
			entityType.getDeclaredId( Long.class );
			fail();
		}
		catch (IllegalArgumentException expected) {
		}

		assertNotNull( mappedSuperclassType.getId( Long.class ) );
		assertNotNull( mappedSuperclassType.getDeclaredId( Long.class ) );
	}

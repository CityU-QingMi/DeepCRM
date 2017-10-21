	@Test
	@TestForIssue( jiraKey = "" )
	public void testAccess() {
		EntityType<FullTimeEmployee> entityType = entityManagerFactory().getMetamodel().entity( FullTimeEmployee.class );
		try {
			entityType.getId( String.class );
			fail( "getId on entity defining @IdClass should cause IAE" );
		}
		catch (IllegalArgumentException expected) {
		}

		assertNotNull( entityType.getSupertype().getIdClassAttributes() );
		assertEquals( 1, entityType.getSupertype().getIdClassAttributes().size() );

		assertFalse( entityType.hasSingleIdAttribute() );

		assertEquals( String.class, entityType.getIdType().getJavaType() );
	}

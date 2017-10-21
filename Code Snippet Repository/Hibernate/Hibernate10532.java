	@Test
	public void testHistory() {
		ObjectUserTypeEntity ver1 = new ObjectUserTypeEntity( id, "builtInType1", "stringUserType1" );
		ObjectUserTypeEntity ver2 = new ObjectUserTypeEntity( id, "builtInType1", 2 );

		Assert.assertEquals( ver1, getAuditReader().find( ObjectUserTypeEntity.class, id, 1 ) );
		Assert.assertEquals( ver2, getAuditReader().find( ObjectUserTypeEntity.class, id, 2 ) );
		Assert.assertEquals(
				ver2,
				getAuditReader().createQuery()
						.forRevisionsOfEntity( ObjectUserTypeEntity.class, true, true )
						.getResultList()
						.get( 2 )
		); // Checking delete state.
	}

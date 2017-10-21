	@Test
	public void testHasChangedPerson1() throws Exception {
		List list = getAuditReader().createQuery().forRevisionsOfEntity( Person.class, "Personaje", false, false )
				.add( AuditEntity.id().eq( id_pers1 ) )
				.add( AuditEntity.property( "cars" ).hasChanged() )
				.getResultList();
		assertEquals( 1, list.size() );
		assertEquals( makeList( 1 ), extractRevisionNumbers( list ) );

		list = getAuditReader().createQuery().forRevisionsOfEntity( Person.class, "Personaje", false, false )
				.add( AuditEntity.id().eq( id_pers1 ) )
				.add( AuditEntity.property( "cars" ).hasNotChanged() )
				.getResultList();
		assertEquals( 1, list.size() );
		assertEquals( makeList( 2 ), extractRevisionNumbers( list ) );
	}

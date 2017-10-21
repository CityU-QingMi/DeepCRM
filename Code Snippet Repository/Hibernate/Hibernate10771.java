	@Test
	public void testHasChangedPerson2() throws Exception {
		List list = getAuditReader().createQuery().forRevisionsOfEntity( Person.class, "Personaje", false, false )
				.add( AuditEntity.id().eq( id_pers2 ) )
				.add( AuditEntity.property( "cars" ).hasChanged() )
				.getResultList();
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 2 ), extractRevisionNumbers( list ) );

		list = getAuditReader().createQuery().forRevisionsOfEntity( Person.class, "Personaje", false, false )
				.add( AuditEntity.id().eq( id_pers2 ) )
				.add( AuditEntity.property( "cars" ).hasNotChanged() )
				.getResultList();
		assertEquals( 0, list.size() );
	}

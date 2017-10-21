	@Test
	public void testHasChangedCar1() throws Exception {
		List list = getAuditReader().createQuery().forRevisionsOfEntity( Car.class, false, false )
				.add( AuditEntity.id().eq( id_car1 ) )
				.add( AuditEntity.property( "owners" ).hasChanged() )
				.getResultList();
		assertEquals( 1, list.size() );
		assertEquals( makeList( 1 ), extractRevisionNumbers( list ) );

		list = getAuditReader().createQuery().forRevisionsOfEntity( Car.class, false, false )
				.add( AuditEntity.id().eq( id_car1 ) )
				.add( AuditEntity.property( "owners" ).hasNotChanged() )
				.getResultList();
		assertEquals( 0, list.size() );
	}

	@Test
	public void testSameTypeConverter() {
		EntityConverter entity = new EntityConverter();
		entity.setSameTypeConverter( "HUNDRED" );
		save( entity );

		entity = find( entity.getId(), "e.sameTypeConverter='HUNDRED'" );

		assertNotNull( entity );
		assertEquals( "HUNDRED", entity.getSameTypeConverter() );

		Session session = openSession();
		String value = (String) session.createSQLQuery( "select e.same_type_converter from entity_converter e where e.id=:id" )
				.setParameter( "id", entity.getId() )
				.uniqueResult();
		assertEquals( "VALUE_HUNDRED", value );
		session.close();
	}

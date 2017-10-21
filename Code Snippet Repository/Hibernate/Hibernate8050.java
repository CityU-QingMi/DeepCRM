	@Test
	public void testMapKeyExpressionInSelect() {
		doInHibernate( this::sessionFactory, s -> {
			// JPA form
			List results = s.createQuery( "select key(v) from TestEntity te join te.values v" ).list();
			assertEquals( 2, results.size() );
			assertTyping( KeyValue.class, results.get( 0 ) );

			// Hibernate additional form
			results = s.createQuery( "select key(te.values) from TestEntity te" ).list();
			assertEquals( 2, results.size() );
			assertTyping( KeyValue.class, results.get( 0 ) );
		} );
	}

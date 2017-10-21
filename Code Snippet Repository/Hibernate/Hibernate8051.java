	@Test
	public void testMapValueExpressionInSelect() {
		doInHibernate( this::sessionFactory, s -> {
			List addresses = s.createQuery( "select value(v) from TestEntity te join te.values v" ).list();
			assertEquals( 2, addresses.size() );
			assertTyping( EmbeddableValue.class, addresses.get( 0 ) );

			addresses = s.createQuery( "select value(te.values) from TestEntity te" ).list();
			assertEquals( 2, addresses.size() );
			assertTyping( EmbeddableValue.class, addresses.get( 0 ) );
		} );
	}

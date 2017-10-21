	@Test
	public void testMapEntryExpressionInSelect() {
		doInHibernate( this::sessionFactory, s -> {
			List addresses = s.createQuery( "select entry(v) from TestEntity te join te.values v" ).list();
			assertEquals( 2, addresses.size() );
			assertTyping( Map.Entry.class, addresses.get( 0 ) );

			addresses = s.createQuery( "select entry(te.values) from TestEntity te" ).list();
			assertEquals( 2, addresses.size() );
			assertTyping( Map.Entry.class, addresses.get( 0 ) );
		} );
	}

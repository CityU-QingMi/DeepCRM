	@Test
	public void testHistoryOfSet1() {
		GreetingSetPO rev1 = getAuditReader().find( GreetingSetPO.class, set1_id, 1 );
		GreetingSetPO rev2 = getAuditReader().find( GreetingSetPO.class, set1_id, 2 );
		GreetingSetPO rev3 = getAuditReader().find( GreetingSetPO.class, set1_id, 3 );

		assert rev1.getName().equals( "a1" );
		assert rev2.getName().equals( "a1" );
		assert rev3.getName().equals( "a1" );

		GreetingPO g1 = new GreetingPO();
		g1.setId( g1_id );
		g1.setGreeting( "g1" );

		assert rev1.getGreetings().size() == 0;
		assert rev2.getGreetings().equals( TestTools.makeSet( g1 ) );
		assert rev3.getGreetings().size() == 0;
	}

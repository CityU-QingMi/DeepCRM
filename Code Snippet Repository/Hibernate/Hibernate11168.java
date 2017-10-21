	@Test
	public void testHistoryOfG1() {
		GreetingPO rev1 = getAuditReader().find( GreetingPO.class, g1_id, 1 );
		GreetingPO rev2 = getAuditReader().find( GreetingPO.class, g1_id, 2 );
		GreetingPO rev3 = getAuditReader().find( GreetingPO.class, g1_id, 3 );

		assert rev1 == null;
		assert rev2.getGreetingSet().getName().equals( "a1" );
		assert rev3.getGreetingSet().getName().equals( "a2" );
	}

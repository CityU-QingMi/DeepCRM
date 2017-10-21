	@Test
	public void testLoading() throws Exception {
		initData();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		Widget obj = (Widget) s.createQuery("from Widget o where o.string = :string").setString("string", "all-normal").uniqueResult();
		assertEquals("Non-Default value incorrectly loaded", obj.getValueOne(), 7);
		assertEquals("Non-Default value incorrectly loaded", obj.getValueTwo(), 8);
		assertEquals("Non-Default value incorrectly loaded", obj.getValueThree(), 9);
		assertEquals("Non-Default value incorrectly loaded", obj.getValueFour(), 10);

		obj = (Widget) s.createQuery("from Widget o where o.string = :string").setString("string", "all-default").uniqueResult();
		assertEquals("Default value incorrectly loaded", obj.getValueOne(), 1);
		assertEquals("Default value incorrectly loaded", obj.getValueTwo(), 2);
		assertEquals("Default value incorrectly loaded", obj.getValueThree(), -1);
		assertEquals("Default value incorrectly loaded", obj.getValueFour(), -5);

		
		t.commit();
		s.close();
		deleteData();
	}

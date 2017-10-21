	@Test
	@TestForIssue( jiraKey = "" )
	public void testBetweenLiteral() {
		final Session s = openSession();
		s.getTransaction().begin();

		@SuppressWarnings("unchecked")
		final List<Item> result = s.createQuery( "from Item where quantity between 9 and 11" ).list();
		assertEquals( 1, result.size() );
		assertEquals( 10, result.get( 0 ).getQuantity().intValue() );

		s.getTransaction().commit();
		s.close();
	}

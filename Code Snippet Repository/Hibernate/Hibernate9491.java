	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testHQL() {
		TestData testData=new TestData();
		testData.createData();
		StatelessSession s = sessionFactory().openStatelessSession();
		assertEquals( 1, s.createQuery( "from Contact c join fetch c.org join fetch c.org.country" )
				.list().size() );
		s.close();
		testData.cleanData();
	}

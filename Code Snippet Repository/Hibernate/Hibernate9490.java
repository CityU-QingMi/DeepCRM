	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testCriteriaWithSelectFetchMode() {
		TestData testData=new TestData();
		testData.createData();
		StatelessSession s = sessionFactory().openStatelessSession();
		assertEquals( 1, s.createCriteria( Contact.class ).setFetchMode( "org", FetchMode.SELECT )
				.list().size() );
		s.close();
		testData.cleanData();
	}

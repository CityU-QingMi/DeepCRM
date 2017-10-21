	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testCriteria() {
		TestData testData=new TestData();
		testData.createData();
		StatelessSession s = sessionFactory().openStatelessSession();
		assertEquals( 1, s.createCriteria( Contact.class ).list().size() );
		s.close();
		testData.cleanData();
	}

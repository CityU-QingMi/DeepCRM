	@Override
	protected void prepareTest() throws Exception {
		openSession();
		session.beginTransaction();
		
		persistTestData();
		
		session.getTransaction().commit();
		session.close();
	}

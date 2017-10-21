	@Override
	protected void cleanupTest() throws Exception {
		super.cleanupTest();
		openSession();
		session.beginTransaction();
		
		session.createQuery("delete from Club").executeUpdate();
		
		session.getTransaction().commit();
		session.close();
	}	

	@Override
	protected void cleanupTest() throws Exception {
		openSession();
		session.beginTransaction();
		
		session.createQuery("delete from Human").executeUpdate();
		
		session.getTransaction().commit();
		session.close();
	}

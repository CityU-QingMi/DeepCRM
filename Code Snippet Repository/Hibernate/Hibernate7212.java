	@Override
	protected void cleanupTest() throws Exception {
		Session s = openSession();
		s.beginTransaction();

		s.createQuery( "delete from org.hibernate.test.cache.User" ).executeUpdate();
		s.createQuery( "delete from org.hibernate.test.cache.Company" ).executeUpdate();

		s.getTransaction().commit();
		s.close();
	}

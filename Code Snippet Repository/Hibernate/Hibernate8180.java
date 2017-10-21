	@After
	public void cleanupData() {
		Session s = openSession();
		s.getTransaction().begin();
		s.createQuery( "delete GrandChild" ).executeUpdate();
		s.createQuery( "delete Child" ).executeUpdate();
		s.createQuery( "delete Parent" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}

	protected void cleanupTest() {
		Session s = openSession();
		s.getTransaction().begin();

		List orders = s.createQuery( "from Order" ).list();

		for( Object order : orders ) {
			s.delete( order );
		}
		s.createQuery( "delete from OrderContact" ).executeUpdate();

		s.createQuery( "delete from OrderLine" ).executeUpdate();

		s.createQuery( "delete from Order" ).executeUpdate();

		s.getTransaction().commit();
		s.close();
	}

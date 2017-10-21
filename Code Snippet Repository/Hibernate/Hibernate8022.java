	public void testTempTableGenerationIsolation() throws Throwable{
		Session s = openSession();
		s.beginTransaction();

		Truck truck = new Truck();
		truck.setVin( "123t" );
		truck.setOwner( "Steve" );
		s.save( truck );

		// manually flush the session to ensure the insert happens
		s.flush();

		// now issue a bulk delete against Car which should force the temp table to be
		// created.  we need to test to ensure that this does not cause the transaction
		// to be committed...
		s.createQuery( "delete from Vehicle" ).executeUpdate();

		s.getTransaction().rollback();
		s.close();

		s = openSession();
		s.beginTransaction();
		List list = s.createQuery( "from Car" ).list();
		assertEquals( "temp table gen caused premature commit", 0, list.size() );
		s.createQuery( "delete from Car" ).executeUpdate();
		s.getTransaction().rollback();
		s.close();
	}

	@Test
	public void testPropertiesRefCascades() {
		Session session = openSession();
		Transaction trans = session.beginTransaction();
		ServerImpl server = new ServerImpl();
		session.save( server );
		AddressImpl address = new AddressImpl();
		server.setAddress( address );
		address.setServer( server );
		session.flush();
		session.createQuery( "from Server s join fetch s.address" ).list();
		trans.commit();
		session.close();

		assertNotNull( server.getId() );
		assertNotNull( address.getId() );

		session = openSession();
		trans = session.beginTransaction();
		session.delete( address );
		session.delete( server );
		trans.commit();
		session.close();
	}

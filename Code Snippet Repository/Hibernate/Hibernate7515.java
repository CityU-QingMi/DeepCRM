	@Test
	public void testElementCollectionConversion() {
		Session session = openSession();
		session.getTransaction().begin();
		Customer customer = new Customer( 1 );
		customer.colors.put( ColorType.BLUE, "favorite" );
		session.persist( customer );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.getTransaction().begin();
		assertEquals( 1, session.get( Customer.class, 1 ).colors.size() );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.getTransaction().begin();
		customer = session.get( Customer.class, 1 );
		session.delete( customer );
		session.getTransaction().commit();
		session.close();
	}

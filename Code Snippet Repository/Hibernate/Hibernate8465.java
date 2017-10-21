	@Test
	@TestForIssue( jiraKey = "" )
	public void testEmbeddedCascadeRemoval() {
		Session session = openSession();

		session.getTransaction().begin();
		Customer customer = new Customer( "Lukasz" );
		Order order1 = new Order( customer, 1L );
		order1.setItem( "laptop" );
		Order order2 = new Order( customer, 2L );
		order2.setItem( "printer" );
		session.save( customer );
		session.save( order1 );
		session.save( order2 );
		session.getTransaction().commit();

		// Removing customer cascades to associated orders.
		session.getTransaction().begin();
		customer = (Customer) session.get( Customer.class, customer.getId() );
		session.delete( customer );
		session.getTransaction().commit();

		session.getTransaction().begin();
		Assert.assertEquals( "0", session.createQuery( "select count(*) from Customer" ).uniqueResult().toString() );
		Assert.assertEquals( "0", session.createQuery( "select count(*) from Order" ).uniqueResult().toString() );
		session.getTransaction().commit();

		session.close();
	}

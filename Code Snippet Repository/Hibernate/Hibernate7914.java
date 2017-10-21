	public void testComponentParameterBinding() {
		Session s = openSession();
		s.beginTransaction();

		Order.Id oId = new Order.Id( "1234", 1 );

		// control
		s.createQuery("from Order o where o.customer.name =:name and o.id = :id")
				.setParameter( "name", "oracle" )
				.setParameter( "id", oId )
				.list();

		// this is the form that caused problems in the original case...
		s.createQuery("from Order o where o.id = :id and o.customer.name =:name ")
				.setParameter( "id", oId )
				.setParameter( "name", "oracle" )
				.list();

		s.getTransaction().commit();
		s.close();
	}

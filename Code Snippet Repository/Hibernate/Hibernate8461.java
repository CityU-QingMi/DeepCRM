	@Test
	public void testCriteriaRestrictionOnKeyManyToOne() {
		Session s = openSession();
		s.beginTransaction();
		s.createQuery( "from Order o where o.customer.name = 'Acme'" ).list();
		Criteria criteria = s.createCriteria( Order.class );
		criteria.createCriteria( "customer" ).add( Restrictions.eq( "name", "Acme" ) );
		criteria.list();
		s.getTransaction().commit();
		s.close();
	}

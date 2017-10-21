	@Before
	public void before() {
		Session s = sessionFactory().openSession();
		s.beginTransaction();
		LocalOrder localOrder = new LocalOrder();
		localOrder.makeLineItem( "Shoes" );
		localOrder.makeLineItem( "Socks" );
		s.save( localOrder );
		s.getTransaction().commit();
		s.close();
	}

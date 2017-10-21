	@Before
	public void setupData() {
		Entity1 e1 = new Entity1();
		e1.setValue( "entity1" );
		Entity2 e2 = new Entity2();
		e2.setValue( "entity2" );
		Entity3 e3 = new Entity3();
		e3.setValue( "entity3" );

		e1.setEntity2( e2 );
		e2.setEntity3( e3 );

		Entity2 e2a = new Entity2();
		e2a.setValue( "entity2a" );

		Session s = openSession();
		s.getTransaction().begin();
		s.persist( e3 );
		s.persist( e2 );
		s.persist( e1 );
		s.persist( e2a );
		s.getTransaction().commit();
		s.close();
	}

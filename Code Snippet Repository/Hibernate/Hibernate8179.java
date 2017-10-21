	@Before
	public void setupData() {
		Parent p = new Parent( "p" );
		Child c1 = new Child( "c1" );
		GrandChild gc11 = new GrandChild( "gc11" );
		GrandChild gc12 = new GrandChild( "gc12" );
		p.getChildren().add( c1 );
		c1.getGrandChildren().add( gc11 );
		c1.getGrandChildren().add( gc12 );

		Child c2 = new Child( "c2" );
		GrandChild gc21 = new GrandChild( "gc21" );
		GrandChild gc22 = new GrandChild( "gc22" );
		GrandChild gc23 = new GrandChild( "gc23" );
		p.getChildren().add( c2 );
		c2.getGrandChildren().add( gc21 );
		c2.getGrandChildren().add( gc22 );
		c2.getGrandChildren().add( gc23 );

		Session s = openSession();
		s.getTransaction().begin();
		s.persist( p );
		s.getTransaction().commit();
		s.close();
	}

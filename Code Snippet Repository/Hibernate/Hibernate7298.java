	@Before
	public void setup() {
		// start by cleaning up in case a test fails
		if ( parentId != null ) {
			cleanup();
		}

		Parent parent = new Parent();
		Child child1 = new Child( "Sherman" );
		Child child2 = new Child( "Yogi" );
		parent.addChild( child1 );
		parent.addChild( child2 );

		Session s = openSession();
		s.getTransaction().begin();
		s.persist( parent );
		s.getTransaction().commit();
		s.close();

		parentId = parent.getId();
		childId1 = child1.getId();
		childId2 = child2.getId();
	}

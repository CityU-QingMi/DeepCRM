	@Before
	public void prepare() {
		Session s = openSession();
		s.getTransaction().begin();
		for ( int i = 0; i < 3; i++ ) {
			DemoEntity entity = new DemoEntity();
			entity.id = new char[] {
					(char) ( i + 1 ),
					(char) ( i + 2 ),
					(char) ( i + 3 ),
					(char) ( i + 4 )
			};
			entity.name = "Simple name " + i;
			s.persist( entity );
		}
		s.getTransaction().commit();
		s.close();
	}

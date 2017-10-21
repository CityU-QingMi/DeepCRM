	@Before
	public void prepare() {
		Session s = openSession();
		s.getTransaction().begin();
		for ( int i = 0; i < 3; i++ ) {
			DemoEntity entity = new DemoEntity();
			entity.id = new byte[] {
					(byte) ( i + 1 ),
					(byte) ( i + 2 ),
					(byte) ( i + 3 ),
					(byte) ( i + 4 )
			};
			entity.name = "Simple name " + i;
			s.persist( entity );
		}
		s.getTransaction().commit();
		s.close();
	}

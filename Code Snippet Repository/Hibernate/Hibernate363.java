	@Before
	public void init() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Library library = new Library();
			library.setId( 1L );
			library.setName( "Amazon" );

			entityManager.persist( library );
		} );
	}

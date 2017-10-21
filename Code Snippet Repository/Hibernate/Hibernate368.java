	@Before
	public void init() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Library library = new Library();
			library.setId( 1L );
			library.setName( "Amazon" );

			entityManager.persist( library );

			Book book = new Book();
			book.setId( 1L );
			book.setTitle( "High-Performance Java Persistence" );
			book.setAuthor( "Vlad Mihalcea" );

			entityManager.persist( book );
		} );
	}

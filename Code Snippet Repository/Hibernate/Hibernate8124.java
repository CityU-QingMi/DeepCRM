	@Before
	public void setUp(){
		doInJPA( this::entityManagerFactory, entityManager -> {
			Price price = new Price( 10, "EUR" );
			Author author = new Author( "Author 1" );
			Book book = new Book( author, "Book 1", price );
			entityManager.persist( book );

			book = new Book( author, "Book 2", price );
			entityManager.persist( book );
		} );
	}

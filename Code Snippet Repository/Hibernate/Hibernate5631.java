	@Before
	public void setUp(){
		doInJPA( this::entityManagerFactory, entityManager -> {
			Price price = new Price( 10, "EUR" );
			Author author = new Author( "Andrea Camilleri" );
			Book book = new Book( author, "Il nipote del Negus", price );
			Bid bid = new Bid( book );
			entityManager.persist( bid );

			book = new Book( author, "La moneta di Akragas", price );
			bid = new Bid( book );
			entityManager.persist( bid );
		} );
	}

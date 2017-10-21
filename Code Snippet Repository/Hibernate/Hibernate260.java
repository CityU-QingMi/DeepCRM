	@Before
	public void init() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person gavinKing = new Person("Gavin", "King" );
			Person stephanKing = new Person("Stephen", "King" );
			Person vladMihalcea = new Person("Vlad", "Mihalcea" );

			gavinKing.addBook( new Book( "Hibernate in Action" ) );
			gavinKing.addBook( new Book( "Java Persistence with Hibernate" ) );

			stephanKing.addBook( new Book( "The Green Mile" ) );

			vladMihalcea.addBook( new Book( "High-Performance Java Persistence" ) );

			entityManager.persist( gavinKing );
			entityManager.persist( stephanKing );
			entityManager.persist( vladMihalcea );
		});
	}

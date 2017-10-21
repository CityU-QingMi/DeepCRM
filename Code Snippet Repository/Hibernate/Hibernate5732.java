	@Before
	public void init() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Book book = new Book();
			book.id = 1;
			book.name = bookName();

			entityManager.persist( book );
		} );
	}

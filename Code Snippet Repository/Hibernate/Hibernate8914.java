	@Before
	public void init() {
		doInHibernate( this::sessionFactory, sess -> {
			Book book1 = new Book( SKU001, WAR_AND_PEACE);
			Book book2 = new Book( SKU002, ANNA_KARENINA);
			sess.persist( book1 );
			sess.flush();
			sess.persist( book2 );
			sess.flush();
			Library library = new Library();
			library.addBook( book1 );
			library.addBook( book2 );
			sess.persist(library);
		} );
	}

	@Before
	public void setUp() {
		buildSessionFactory();
		final Set messagesPrefixes = new HashSet<>();
		messagesPrefixes.add( "HHH000444" );
		messagesPrefixes.add( "HHH000445" );
		triggerable = logInspection.watchForLogMessages( messagesPrefixes );
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			Item item = new Item();
			item.name = "ZZZZ";
			session.persist( item );
		} );
	}

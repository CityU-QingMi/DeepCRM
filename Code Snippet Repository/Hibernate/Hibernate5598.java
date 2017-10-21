	@Before
	public void setUp(){
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			Customer customer = new Customer();
			customer.setId( "id" );
			customer.setName( " David R. Vincent " );
			entityManager.persist( customer );
			customer = new Customer();
			customer.setId( "id2" );
			customer.setName( "R Vincent" );
			entityManager.persist( customer );
		} );
	}

	@Before
	public void setUp() {
		final Session s = openSession();
		s.getTransaction().begin();
		try {
			s.save( new LocalDateEvent( 1L, expectedLocalDate ) );
			s.getTransaction().commit();
		}
		catch (Exception e) {
			if ( s.getTransaction() != null && s.getTransaction().getStatus() == TransactionStatus.ACTIVE ) {
				s.getTransaction().rollback();
			}
			fail( e.getMessage() );
		}
		finally {
			s.close();
		}
	}

	@Before
	public void setup() {
		session = openSession();
		Transaction transaction = session.beginTransaction();

		final SubSubEntity subSubEntity = new SubSubEntity();
		final SubEntity subEntity = new SubSubEntity();
		try {
			session.save( subEntity );
			session.save( subSubEntity );
			transaction.commit();
			subSubEntityId = subSubEntity.getId();
		}
		finally {
			if ( transaction.getStatus() == TransactionStatus.ACTIVE ) {
				transaction.rollback();
			}
		}
		session.close();
	}

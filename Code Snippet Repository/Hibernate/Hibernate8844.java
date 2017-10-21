	@After
	public void tearDown() {
		Session s = openSession();
		s.getTransaction().begin();
		try {
			final Query query = s.createQuery( "delete from NationalizedEntity" );
			query.executeUpdate();
			s.getTransaction().commit();
		}
		catch (RuntimeException e) {
			if ( s.getTransaction().getStatus() == TransactionStatus.ACTIVE ) {
				s.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			s.close();
		}
	}

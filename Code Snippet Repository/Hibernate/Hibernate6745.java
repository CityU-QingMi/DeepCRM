	@Test
	public void testGetNotExisting() {
		Session s = openSession();
		s.beginTransaction();

		try {
			long notExistingId = 1l;
			s.load( Team.class, notExistingId );
			s.get( Team.class, notExistingId );
			s.getTransaction().commit();
		}
		catch (ObjectNotFoundException e) {
			if ( s.getTransaction().getStatus() == TransactionStatus.ACTIVE ) {
				s.getTransaction().rollback();
			}
			fail("#get threw an ObjectNotFoundExcepton");
		}
		finally {
			s.close();
		}
	}

	private void saveOffsetDateTimeEventWithStartDate(OffsetDateTime startdate) {
		final OffsetDateTimeEvent event = new OffsetDateTimeEvent();
		event.id = 1L;
		event.startDate = startdate;

		final Session s = openSession();
		s.getTransaction().begin();
		try {
			s.save( event );
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

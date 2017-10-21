	private void saveZoneDateTimeEventWithStartDate(ZonedDateTime startdate) {
		final ZonedDateTimeEvent event = new ZonedDateTimeEvent();
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

	@Test
	public void testInheritance() throws Exception {
		Session session = openSession();
		Transaction transaction = session.beginTransaction();
		String eventPK = "event1";
		EventInformation event = (EventInformation) session.get( EventInformation.class, eventPK );
		if ( event == null ) {
			event = new EventInformation();
			event.setNotificationId( eventPK );
			session.persist( event );
		}
		String alarmPK = "alarm1";
		Alarm alarm = (Alarm) session.get( Alarm.class, alarmPK );
		if ( alarm == null ) {
			alarm = new Alarm();
			alarm.setNotificationId( alarmPK );
			alarm.setEventInfo( event );
			session.persist( alarm );
		}
		transaction.commit();
		session.close();
	}

	public Long createHolidayCalendar() {

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		// delete all existing calendars
		List calendars = session.createQuery("from HolidayCalendar").setCacheable(true).list();
		for (ListIterator li = calendars.listIterator(); li.hasNext(); ) {
			session.delete(li.next());
		}

		HolidayCalendar calendar = new HolidayCalendar();
		calendar.init();

		Long calendarId = (Long)session.save(calendar);

		session.getTransaction().commit();
		return calendarId;
	}

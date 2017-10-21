	public HolidayCalendar getHolidayCalendar() {
		Session session = sessionFactory.getCurrentSession();

		session.beginTransaction();

		List calendars = session.createQuery("from HolidayCalendar").setCacheable(true).list();

		session.getTransaction().commit();

		return calendars.isEmpty() ? null : (HolidayCalendar)calendars.get(0);
	}

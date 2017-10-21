	@Test
	public void testTemporalType() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		
		DataPoint dp = new DataPoint();
		dp.date1 = date;
		dp.date2 = date;
		dp.calendar1 = calendar;
		dp.calendar2 = calendar;
		em.persist( dp );
		
		em.getTransaction().commit();
		em.close();

		doTest("date1", date);
		doTest("date1", calendar);
		doTest("date2", date);
		doTest("date2", calendar);

		doTest("calendar1", date);
		doTest("calendar1", calendar);
		doTest("calendar2", date);
		doTest("calendar2", calendar);
	}

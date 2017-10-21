	@Test
	public void testDatesInJmx() throws Exception {
		// System.out.println(getServer().getClass().getName());
		ObjectName oname = new ObjectName("bean:name=dateRange");

		Date startJmx = (Date) getServer().getAttribute(oname, "StartDate");
		Date endJmx = (Date) getServer().getAttribute(oname, "EndDate");

		assertEquals("startDate ", getStartDate(), startJmx);
		assertEquals("endDate ", getEndDate(), endJmx);
	}

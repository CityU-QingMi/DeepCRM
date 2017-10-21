	@Test
	public void customizeDateFormatNone() throws Exception {
		this.factory.afterPropertiesSet();
		Gson gson = this.factory.getObject();
		DateBean bean = new DateBean();
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 1);
		Date date = cal.getTime();
		bean.setDate(date);
		String result = gson.toJson(bean);
		assertTrue(result.startsWith("{\"date\":\"Jan 1, 2014"));
		assertTrue(result.endsWith("12:00:00 AM\"}"));
	}

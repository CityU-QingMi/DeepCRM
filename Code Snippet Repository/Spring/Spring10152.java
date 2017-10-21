	@Test
	public void customizeDateFormatPattern() throws Exception {
		this.factory.setDateFormatPattern(DATE_FORMAT);
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
		assertEquals("{\"date\":\"2014-01-01\"}", result);
	}

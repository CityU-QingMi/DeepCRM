		@InitBinder({"", ""})
		public void initBinder(WebDataBinder binder, String date, @RequestParam("date") String[] date2) {
			LocalValidatorFactoryBean vf = new LocalValidatorFactoryBean();
			vf.afterPropertiesSet();
			binder.setValidator(vf);
			assertEquals("2007-10-02", date);
			assertEquals(1, date2.length);
			assertEquals("2007-10-02", date2[0]);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat.setLenient(false);
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		}

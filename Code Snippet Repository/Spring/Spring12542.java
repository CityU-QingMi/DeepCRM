	@Test
	public void testServletContextParameterFactoryBean() {
		MockServletContext sc = new MockServletContext();
		sc.addInitParameter("myParam", "myValue");

		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.setServletContext(sc);
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("initParamName", "myParam");
		wac.registerSingleton("importedParam", ServletContextParameterFactoryBean.class, pvs);
		wac.refresh();

		Object value = wac.getBean("importedParam");
		assertEquals("myValue", value);
	}

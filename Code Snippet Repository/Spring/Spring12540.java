	@Test
	public void testServletContextAttributeFactoryBean() {
		MockServletContext sc = new MockServletContext();
		sc.setAttribute("myAttr", "myValue");

		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.setServletContext(sc);
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("attributeName", "myAttr");
		wac.registerSingleton("importedAttr", ServletContextAttributeFactoryBean.class, pvs);
		wac.refresh();

		Object value = wac.getBean("importedAttr");
		assertEquals("myValue", value);
	}

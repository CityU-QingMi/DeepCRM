	private WebApplicationContext initApplicationContext(String scope) {
		MockServletContext sc = new MockServletContext();
		GenericWebApplicationContext ac = new GenericWebApplicationContext(sc);
		GenericBeanDefinition bd = new GenericBeanDefinition();
		bd.setBeanClass(DerivedTestBean.class);
		bd.setScope(scope);
		ac.registerBeanDefinition(NAME, bd);
		ac.refresh();
		return ac;
	}

	@Test
	public void testBeanNameViewResolver() throws ServletException {
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.setServletContext(new MockServletContext());
		MutablePropertyValues pvs1 = new MutablePropertyValues();
		pvs1.addPropertyValue(new PropertyValue("url", "/example1.jsp"));
		wac.registerSingleton("example1", InternalResourceView.class, pvs1);
		MutablePropertyValues pvs2 = new MutablePropertyValues();
		pvs2.addPropertyValue(new PropertyValue("url", "/example2.jsp"));
		wac.registerSingleton("example2", JstlView.class, pvs2);
		BeanNameViewResolver vr = new BeanNameViewResolver();
		vr.setApplicationContext(wac);
		wac.refresh();

		View view = vr.resolveViewName("example1", Locale.getDefault());
		assertEquals("Correct view class", InternalResourceView.class, view.getClass());
		assertEquals("Correct URL", "/example1.jsp", ((InternalResourceView) view).getUrl());

		view = vr.resolveViewName("example2", Locale.getDefault());
		assertEquals("Correct view class", JstlView.class, view.getClass());
		assertEquals("Correct URL", "/example2.jsp", ((JstlView) view).getUrl());
	}

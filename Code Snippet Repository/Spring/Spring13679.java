	@Test
	public void eagerInitialization() throws Exception {
		ResourceBundleViewResolver rb = new ResourceBundleViewResolver();
		rb.setBasename(PROPS_FILE);
		rb.setCache(getCache());
		rb.setDefaultParentView("testParent");
		rb.setLocalesToInitialize(new Locale[] {Locale.ENGLISH, Locale.FRENCH});
		rb.setApplicationContext(wac);

		View v = rb.resolveViewName("debugView", Locale.FRENCH);
		assertThat(v, instanceOf(InternalResourceView.class));
		InternalResourceView jv = (InternalResourceView) v;
		assertEquals("French debugView must have correct URL", "jsp/debug/deboug.jsp", jv.getUrl());
		assertEquals("Correct overridden (XML) content type", "text/xml;charset=ISO-8859-1", jv.getContentType());
	}

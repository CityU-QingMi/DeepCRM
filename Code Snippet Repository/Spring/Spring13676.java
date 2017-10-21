	@Before
	public void setUp() throws Exception {
		rb.setBasename(PROPS_FILE);
		rb.setCache(getCache());
		rb.setDefaultParentView("testParent");

		wac.setServletContext(new MockServletContext());
		wac.refresh();

		// This will be propagated to views, so we need it.
		rb.setApplicationContext(wac);
	}

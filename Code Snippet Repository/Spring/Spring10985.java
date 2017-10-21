	@Test
	public void getScopeSunnyDay() {
		assertEquals(TagUtils.SCOPE_PAGE, "page");
		assertEquals(TagUtils.SCOPE_APPLICATION, "application");
		assertEquals(TagUtils.SCOPE_SESSION, "session");
		assertEquals(TagUtils.SCOPE_REQUEST, "request");

		assertEquals(PageContext.PAGE_SCOPE, TagUtils.getScope("page"));
		assertEquals(PageContext.REQUEST_SCOPE, TagUtils.getScope("request"));
		assertEquals(PageContext.SESSION_SCOPE, TagUtils.getScope("session"));
		assertEquals(PageContext.APPLICATION_SCOPE, TagUtils.getScope("application"));

		// non-existent scope
		assertEquals("TagUtils.getScope(..) with a non-existent scope argument must " +
				"just return the default scope (PageContext.PAGE_SCOPE).", PageContext.PAGE_SCOPE,
				TagUtils.getScope("bla"));
	}

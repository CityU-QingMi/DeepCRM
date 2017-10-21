	@Test
	public void defaultValuesForRedirect() throws Exception {
		Rendering rendering = Rendering.redirectTo("abc").build();

		Object view = rendering.view();
		assertEquals(RedirectView.class, view.getClass());
		assertEquals("abc", ((RedirectView) view).getUrl());
		assertTrue(((RedirectView) view).isContextRelative());
		assertFalse(((RedirectView) view).isPropagateQuery());
	}

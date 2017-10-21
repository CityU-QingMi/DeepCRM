	@Test
	public void parameterizableViewController() throws Exception {
		String viewName = "viewName";
		ParameterizableViewController pvc = new ParameterizableViewController();
		pvc.setViewName(viewName);
		// We don't care about the params.
		ModelAndView mv = pvc.handleRequest(new MockHttpServletRequest("GET", "foo.html"), new MockHttpServletResponse());
		assertTrue("model has no data", mv.getModel().size() == 0);
		assertTrue("model has correct viewname", mv.getViewName().equals(viewName));
		assertTrue("getViewName matches", pvc.getViewName().equals(viewName));
	}

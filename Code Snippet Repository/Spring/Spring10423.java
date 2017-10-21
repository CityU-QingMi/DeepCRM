	@Test
	public void testLongParameter() throws ServletRequestBindingException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("param1", "5");
		request.addParameter("param2", "e");
		request.addParameter("paramEmpty", "");

		assertEquals(ServletRequestUtils.getLongParameter(request, "param1"), new Long(5L));
		assertEquals(ServletRequestUtils.getLongParameter(request, "param1", 6L), 5L);
		assertEquals(ServletRequestUtils.getRequiredIntParameter(request, "param1"), 5L);

		assertEquals(ServletRequestUtils.getLongParameter(request, "param2", 6L), 6L);
		try {
			ServletRequestUtils.getRequiredLongParameter(request, "param2");
			fail("Should have thrown ServletRequestBindingException");
		}
		catch (ServletRequestBindingException ex) {
			// expected
		}

		assertEquals(ServletRequestUtils.getLongParameter(request, "param3"), null);
		assertEquals(ServletRequestUtils.getLongParameter(request, "param3", 6L), 6L);
		try {
			ServletRequestUtils.getRequiredLongParameter(request, "param3");
			fail("Should have thrown ServletRequestBindingException");
		}
		catch (ServletRequestBindingException ex) {
			// expected
		}

		try {
			ServletRequestUtils.getRequiredLongParameter(request, "paramEmpty");
			fail("Should have thrown ServletRequestBindingException");
		}
		catch (ServletRequestBindingException ex) {
			// expected
		}
	}

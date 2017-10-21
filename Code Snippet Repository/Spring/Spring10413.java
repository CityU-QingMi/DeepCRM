	@Test
	public void testIntParameter() throws ServletRequestBindingException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("param1", "5");
		request.addParameter("param2", "e");
		request.addParameter("paramEmpty", "");

		assertEquals(ServletRequestUtils.getIntParameter(request, "param1"), new Integer(5));
		assertEquals(ServletRequestUtils.getIntParameter(request, "param1", 6), 5);
		assertEquals(ServletRequestUtils.getRequiredIntParameter(request, "param1"), 5);

		assertEquals(ServletRequestUtils.getIntParameter(request, "param2", 6), 6);
		try {
			ServletRequestUtils.getRequiredIntParameter(request, "param2");
			fail("Should have thrown ServletRequestBindingException");
		}
		catch (ServletRequestBindingException ex) {
			// expected
		}

		assertEquals(ServletRequestUtils.getIntParameter(request, "param3"), null);
		assertEquals(ServletRequestUtils.getIntParameter(request, "param3", 6), 6);
		try {
			ServletRequestUtils.getRequiredIntParameter(request, "param3");
			fail("Should have thrown ServletRequestBindingException");
		}
		catch (ServletRequestBindingException ex) {
			// expected
		}

		try {
			ServletRequestUtils.getRequiredIntParameter(request, "paramEmpty");
			fail("Should have thrown ServletRequestBindingException");
		}
		catch (ServletRequestBindingException ex) {
			// expected
		}
	}

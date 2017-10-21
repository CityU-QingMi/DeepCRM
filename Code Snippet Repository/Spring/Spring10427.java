	@Test
	public void testDoubleParameter() throws ServletRequestBindingException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("param1", "5.5");
		request.addParameter("param2", "e");
		request.addParameter("paramEmpty", "");

		assertTrue(ServletRequestUtils.getDoubleParameter(request, "param1").equals(new Double(5.5)));
		assertTrue(ServletRequestUtils.getDoubleParameter(request, "param1", 6.5) == 5.5);
		assertTrue(ServletRequestUtils.getRequiredDoubleParameter(request, "param1") == 5.5);

		assertTrue(ServletRequestUtils.getDoubleParameter(request, "param2", 6.5) == 6.5);
		try {
			ServletRequestUtils.getRequiredDoubleParameter(request, "param2");
			fail("Should have thrown ServletRequestBindingException");
		}
		catch (ServletRequestBindingException ex) {
			// expected
		}

		assertTrue(ServletRequestUtils.getDoubleParameter(request, "param3") == null);
		assertTrue(ServletRequestUtils.getDoubleParameter(request, "param3", 6.5) == 6.5);
		try {
			ServletRequestUtils.getRequiredDoubleParameter(request, "param3");
			fail("Should have thrown ServletRequestBindingException");
		}
		catch (ServletRequestBindingException ex) {
			// expected
		}

		try {
			ServletRequestUtils.getRequiredDoubleParameter(request, "paramEmpty");
			fail("Should have thrown ServletRequestBindingException");
		}
		catch (ServletRequestBindingException ex) {
			// expected
		}
	}

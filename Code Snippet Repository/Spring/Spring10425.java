	@Test
	public void testFloatParameter() throws ServletRequestBindingException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("param1", "5.5");
		request.addParameter("param2", "e");
		request.addParameter("paramEmpty", "");

		assertTrue(ServletRequestUtils.getFloatParameter(request, "param1").equals(new Float(5.5f)));
		assertTrue(ServletRequestUtils.getFloatParameter(request, "param1", 6.5f) == 5.5f);
		assertTrue(ServletRequestUtils.getRequiredFloatParameter(request, "param1") == 5.5f);

		assertTrue(ServletRequestUtils.getFloatParameter(request, "param2", 6.5f) == 6.5f);
		try {
			ServletRequestUtils.getRequiredFloatParameter(request, "param2");
			fail("Should have thrown ServletRequestBindingException");
		}
		catch (ServletRequestBindingException ex) {
			// expected
		}

		assertTrue(ServletRequestUtils.getFloatParameter(request, "param3") == null);
		assertTrue(ServletRequestUtils.getFloatParameter(request, "param3", 6.5f) == 6.5f);
		try {
			ServletRequestUtils.getRequiredFloatParameter(request, "param3");
			fail("Should have thrown ServletRequestBindingException");
		}
		catch (ServletRequestBindingException ex) {
			// expected
		}

		try {
			ServletRequestUtils.getRequiredFloatParameter(request, "paramEmpty");
			fail("Should have thrown ServletRequestBindingException");
		}
		catch (ServletRequestBindingException ex) {
			// expected
		}
	}

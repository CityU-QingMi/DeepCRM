	@Test
	public void testBooleanParameter() throws ServletRequestBindingException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("param1", "true");
		request.addParameter("param2", "e");
		request.addParameter("param4", "yes");
		request.addParameter("param5", "1");
		request.addParameter("paramEmpty", "");

		assertTrue(ServletRequestUtils.getBooleanParameter(request, "param1").equals(Boolean.TRUE));
		assertTrue(ServletRequestUtils.getBooleanParameter(request, "param1", false));
		assertTrue(ServletRequestUtils.getRequiredBooleanParameter(request, "param1"));

		assertFalse(ServletRequestUtils.getBooleanParameter(request, "param2", true));
		assertFalse(ServletRequestUtils.getRequiredBooleanParameter(request, "param2"));

		assertTrue(ServletRequestUtils.getBooleanParameter(request, "param3") == null);
		assertTrue(ServletRequestUtils.getBooleanParameter(request, "param3", true));
		try {
			ServletRequestUtils.getRequiredBooleanParameter(request, "param3");
			fail("Should have thrown ServletRequestBindingException");
		}
		catch (ServletRequestBindingException ex) {
			// expected
		}

		assertTrue(ServletRequestUtils.getBooleanParameter(request, "param4", false));
		assertTrue(ServletRequestUtils.getRequiredBooleanParameter(request, "param4"));

		assertTrue(ServletRequestUtils.getBooleanParameter(request, "param5", false));
		assertTrue(ServletRequestUtils.getRequiredBooleanParameter(request, "param5"));
		assertFalse(ServletRequestUtils.getRequiredBooleanParameter(request, "paramEmpty"));
	}

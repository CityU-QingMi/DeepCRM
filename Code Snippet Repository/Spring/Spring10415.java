	@Test
	public void testStringParameter() throws ServletRequestBindingException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("param1", "str");
		request.addParameter("paramEmpty", "");

		assertEquals("str", ServletRequestUtils.getStringParameter(request, "param1"));
		assertEquals("str", ServletRequestUtils.getStringParameter(request, "param1", "string"));
		assertEquals("str", ServletRequestUtils.getRequiredStringParameter(request, "param1"));

		assertEquals(null, ServletRequestUtils.getStringParameter(request, "param3"));
		assertEquals("string", ServletRequestUtils.getStringParameter(request, "param3", "string"));
		assertNull(ServletRequestUtils.getStringParameter(request, "param3", null));
		try {
			ServletRequestUtils.getRequiredStringParameter(request, "param3");
			fail("Should have thrown ServletRequestBindingException");
		}
		catch (ServletRequestBindingException ex) {
			// expected
		}

		assertEquals("", ServletRequestUtils.getStringParameter(request, "paramEmpty"));
		assertEquals("", ServletRequestUtils.getRequiredStringParameter(request, "paramEmpty"));
	}

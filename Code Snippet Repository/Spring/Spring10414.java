	@Test
	public void testBooleanParameters() throws ServletRequestBindingException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("param", new String[] {"true", "yes", "off", "1", "bogus"});

		request.addParameter("param2", "false");
		request.addParameter("param2", "true");
		request.addParameter("param2", "");

		boolean[] array = new boolean[] {true, true, false, true, false};
		boolean[] values = ServletRequestUtils.getRequiredBooleanParameters(request, "param");
		assertEquals(array.length, values.length);
		for (int i = 0; i < array.length; i++) {
			assertEquals(array[i], values[i]);
		}

		array = new boolean[] {false, true, false};
		values = ServletRequestUtils.getRequiredBooleanParameters(request, "param2");
		assertEquals(array.length, values.length);
		for (int i = 0; i < array.length; i++) {
			assertEquals(array[i], values[i]);
		}
	}

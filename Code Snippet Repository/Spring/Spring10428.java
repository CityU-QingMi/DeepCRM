	@Test
	public void testDoubleParameters() throws ServletRequestBindingException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("param", new String[] {"1.5", "2.5", "3"});

		request.addParameter("param2", "1.5");
		request.addParameter("param2", "2");
		request.addParameter("param2", "bogus");

		double[] array = new double[] {1.5, 2.5, 3};
		double[] values = ServletRequestUtils.getRequiredDoubleParameters(request, "param");
		assertEquals(3, values.length);
		for (int i = 0; i < array.length; i++) {
			assertEquals(array[i], values[i], 0);
		}

		try {
			ServletRequestUtils.getRequiredDoubleParameters(request, "param2");
			fail("Should have thrown ServletRequestBindingException");
		}
		catch (ServletRequestBindingException ex) {
			// expected
		}
	}

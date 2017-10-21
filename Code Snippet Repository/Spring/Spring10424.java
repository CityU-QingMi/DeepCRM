	@Test
	public void testLongParameters() throws ServletRequestBindingException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("param", new String[] {"1", "2", "3"});

		request.setParameter("param2", "0");
		request.setParameter("param2", "1");
		request.addParameter("param2", "2");
		request.addParameter("param2", "bogus");

		long[] array = new long[] {1L, 2L, 3L};
		long[] values = ServletRequestUtils.getRequiredLongParameters(request, "param");
		assertEquals(3, values.length);
		for (int i = 0; i < array.length; i++) {
			assertEquals(array[i], values[i]);
		}

		try {
			ServletRequestUtils.getRequiredLongParameters(request, "param2");
			fail("Should have thrown ServletRequestBindingException");
		}
		catch (ServletRequestBindingException ex) {
			// expected
		}

		request.setParameter("param2", new String[] {"1", "2"});
		values = ServletRequestUtils.getRequiredLongParameters(request, "param2");
		assertEquals(2, values.length);
		assertEquals(1, values[0]);
		assertEquals(2, values[1]);

		request.removeParameter("param2");
		try {
			ServletRequestUtils.getRequiredLongParameters(request, "param2");
			fail("Should have thrown ServletRequestBindingException");
		}
		catch (ServletRequestBindingException ex) {
			// expected
		}
	}

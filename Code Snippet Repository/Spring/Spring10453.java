	@Test
	public void testMultipleValuesForParameter() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		String[] original = new String[] {"Tony", "Rod"};
		request.addParameter("forname", original);

		ServletRequestParameterPropertyValues pvs = new ServletRequestParameterPropertyValues(request);
		assertTrue("Found 1 parameter", pvs.getPropertyValues().length == 1);
		assertTrue("Found array value", pvs.getPropertyValue("forname").getValue() instanceof String[]);
		String[] values = (String[]) pvs.getPropertyValue("forname").getValue();
		assertEquals("Correct values", Arrays.asList(values), Arrays.asList(original));
	}

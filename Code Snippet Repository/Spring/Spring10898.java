	private void doTestParameters(MultipartHttpServletRequest request) {
		Set<String> parameterNames = new HashSet<>();
		Enumeration<String> parameterEnum = request.getParameterNames();
		while (parameterEnum.hasMoreElements()) {
			parameterNames.add(parameterEnum.nextElement());
		}
		assertEquals(3, parameterNames.size());
		assertTrue(parameterNames.contains("field3"));
		assertTrue(parameterNames.contains("field4"));
		assertTrue(parameterNames.contains("getField"));
		assertEquals("value3", request.getParameter("field3"));
		List<String> parameterValues = Arrays.asList(request.getParameterValues("field3"));
		assertEquals(1, parameterValues.size());
		assertTrue(parameterValues.contains("value3"));
		assertEquals("value4", request.getParameter("field4"));
		parameterValues = Arrays.asList(request.getParameterValues("field4"));
		assertEquals(2, parameterValues.size());
		assertTrue(parameterValues.contains("value4"));
		assertTrue(parameterValues.contains("value5"));
		assertEquals("value4", request.getParameter("field4"));
		assertEquals("getValue", request.getParameter("getField"));

		List<String> parameterMapKeys = new ArrayList<>();
		List<Object> parameterMapValues = new ArrayList<>();
		for (Object o : request.getParameterMap().keySet()) {
			String key = (String) o;
			parameterMapKeys.add(key);
			parameterMapValues.add(request.getParameterMap().get(key));
		}
		assertEquals(3, parameterMapKeys.size());
		assertEquals(3, parameterMapValues.size());
		int field3Index = parameterMapKeys.indexOf("field3");
		int field4Index = parameterMapKeys.indexOf("field4");
		int getFieldIndex = parameterMapKeys.indexOf("getField");
		assertTrue(field3Index != -1);
		assertTrue(field4Index != -1);
		assertTrue(getFieldIndex != -1);
		parameterValues = Arrays.asList((String[]) parameterMapValues.get(field3Index));
		assertEquals(1, parameterValues.size());
		assertTrue(parameterValues.contains("value3"));
		parameterValues = Arrays.asList((String[]) parameterMapValues.get(field4Index));
		assertEquals(2, parameterValues.size());
		assertTrue(parameterValues.contains("value4"));
		assertTrue(parameterValues.contains("value5"));
		parameterValues = Arrays.asList((String[]) parameterMapValues.get(getFieldIndex));
		assertEquals(1, parameterValues.size());
		assertTrue(parameterValues.contains("getValue"));
	}

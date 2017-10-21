	@Test
	public void removeAllParameters() {
		request.setParameter("key1", "value1");
		Map<String, Object> params = new HashMap<>(2);
		params.put("key2", "value2");
		params.put("key3", new String[] { "value3A", "value3B" });
		request.addParameters(params);
		assertEquals(3, request.getParameterMap().size());
		request.removeAllParameters();
		assertEquals(0, request.getParameterMap().size());
	}

	@Test
	public void testAddMap() throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("one", "one-value");
		map.put("two", "two-value");
		ModelMap model = new ModelMap();
		model.addAttribute(map);
		assertEquals(1, model.size());
		String key = StringUtils.uncapitalize(ClassUtils.getShortName(map.getClass()));
		assertTrue(model.containsKey(key));
	}

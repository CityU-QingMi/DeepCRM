	@Test
	public void testOneArgCtorWithCollection() throws Exception {
		ModelMap model = new ModelMap(new String[]{"foo", "boing"});
		assertEquals(1, model.size());
		String[] strings = (String[]) model.get("stringList");
		assertNotNull(strings);
		assertEquals(2, strings.length);
		assertEquals("foo", strings[0]);
		assertEquals("boing", strings[1]);
	}

	public void testDefaultAllow1() throws JSONException {

		JSONCleaner cleaner = getCleaner();
		cleaner.setDefaultBlock(false);
		cleaner.setBlocked("b,d");

		Map data = getData();
		cleaner.clean("", data);
		assertEquals(2, data.size());
		assertEquals("x", data.get("a"));
		assertNull(data.get("b"));
		assertNotNull(data.get("c"));
		assertNull(data.get("d"));

	}

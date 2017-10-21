	public void testDefaultAllow2() throws JSONException {

		JSONCleaner cleaner = getCleaner();
		cleaner.setDefaultBlock(false);
		cleaner.setBlocked("b,d.y");

		Map data = getData();
		cleaner.clean("", data);
		assertEquals(3, data.size());
		assertEquals("x", data.get("a"));
		assertNull(data.get("b"));
		assertNotNull(data.get("c"));
		assertNotNull(data.get("d"));
		assertEquals(1, ((Map) data.get("d")).size());
		assertEquals("a", ((Map) data.get("d")).get("x"));
		assertNull(((Map) data.get("d")).get("y"));

	}

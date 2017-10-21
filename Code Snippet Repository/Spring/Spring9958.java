	@Test
	public void testEquals() {
		MultiValueMap<String, String> map1 = new LinkedMultiValueMap<>();
		map1.set("Content-Type", "text/plain");

		MultiValueMap<String, String> map2 = new LinkedMultiValueMap<>();
		map2.set("Content-Type", "application/json");

		assertTrue(new HttpEntity<>().equals(new HttpEntity<Object>()));
		assertFalse(new HttpEntity<>(map1).equals(new HttpEntity<Object>()));
		assertFalse(new HttpEntity<>().equals(new HttpEntity<Object>(map2)));

		assertTrue(new HttpEntity<>(map1).equals(new HttpEntity<Object>(map1)));
		assertFalse(new HttpEntity<>(map1).equals(new HttpEntity<Object>(map2)));

		assertTrue(new HttpEntity<String>(null, null).equals(new HttpEntity<String>(null, null)));
		assertFalse(new HttpEntity<>("foo", null).equals(new HttpEntity<String>(null, null)));
		assertFalse(new HttpEntity<String>(null, null).equals(new HttpEntity<>("bar", null)));

		assertTrue(new HttpEntity<>("foo", map1).equals(new HttpEntity<String>("foo", map1)));
		assertFalse(new HttpEntity<>("foo", map1).equals(new HttpEntity<String>("bar", map1)));
	}

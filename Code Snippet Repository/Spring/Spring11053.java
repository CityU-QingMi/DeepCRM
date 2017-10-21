	@Test
	public void isValidOrigin() {
		List<String> allowed = Collections.emptyList();
		assertTrue(checkValidOrigin("mydomain1.com", -1, "http://mydomain1.com", allowed));
		assertFalse(checkValidOrigin("mydomain1.com", -1, "http://mydomain2.com", allowed));

		allowed = Collections.singletonList("*");
		assertTrue(checkValidOrigin("mydomain1.com", -1, "http://mydomain2.com", allowed));

		allowed = Collections.singletonList("http://mydomain1.com");
		assertTrue(checkValidOrigin("mydomain2.com", -1, "http://mydomain1.com", allowed));
		assertFalse(checkValidOrigin("mydomain2.com", -1, "http://mydomain3.com", allowed));
	}

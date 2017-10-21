	@Test
	public void buildAndExpandOpaque() {
		UriComponents result = UriComponentsBuilder.fromUriString("mailto:{user}@{domain}").buildAndExpand("foo", "example.com");
		assertEquals("mailto:foo@example.com", result.toUriString());

		Map<String, String> values = new HashMap<>();
		values.put("user", "foo");
		values.put("domain", "example.com");
		UriComponentsBuilder.fromUriString("mailto:{user}@{domain}").buildAndExpand(values);
		assertEquals("mailto:foo@example.com", result.toUriString());
	}

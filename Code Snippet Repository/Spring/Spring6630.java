	@Test
	public void multipleKeys() {
		Map<String, Object> m = new HashMap<String, Object>() {{
			put("key", 1);
			put("seq", 2);
		}};
		kh.getKeyList().addAll(singletonList(m));

		assertEquals("two keys should be in the map", 2, kh.getKeys().size());
		exception.expect(InvalidDataAccessApiUsageException.class);
		exception.expectMessage(startsWith("The getKey method should only be used when a single key is returned."));
		kh.getKey();
	}

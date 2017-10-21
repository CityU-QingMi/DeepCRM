	@Test
	public void multipleKeyRows() {
		Map<String, Object> m = new HashMap<String, Object>() {{
			put("key", 1);
			put("seq", 2);
		}};
		kh.getKeyList().addAll(asList(m, m));

		assertEquals("two rows should be in the list", 2, kh.getKeyList().size());
		exception.expect(InvalidDataAccessApiUsageException.class);
		exception.expectMessage(startsWith("The getKeys method should only be used when keys for a single row are returned."));
		kh.getKeys();
	}

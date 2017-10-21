	@Test
	@SuppressWarnings("")
	void withMapField() {
		// @formatter:off
		Map<String,Object> map = new LinkedHashMap<String,Object>() {{
			put("foo", 42);
			put("bar", "enigma");
		}};
		// @formatter:on
		assertEquals("RoleModel [mystery map = {foo=42, bar=enigma}]",
			new ToStringBuilder(new RoleModel()).append("mystery map", map).toString());
	}

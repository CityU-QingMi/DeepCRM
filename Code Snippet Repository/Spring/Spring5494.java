	@Test
	public void testWithResolver() {
		String text = "foo=${foo}";

		assertEquals("foo=bar",
				this.helper.replacePlaceholders(text, new PropertyPlaceholderHelper.PlaceholderResolver() {
					@Override
					public String resolvePlaceholder(String placeholderName) {
						if ("foo".equals(placeholderName)) {
							return "bar";
						}
						else {
							return null;
						}
					}
				}));
	}

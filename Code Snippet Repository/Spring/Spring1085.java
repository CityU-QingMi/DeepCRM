	@Test
	public void withShadowedField() throws Exception {
		final StringBuilder sb = new StringBuilder();

		@SuppressWarnings("serial")
		TestBean target = new TestBean() {
			@SuppressWarnings("unused")
			StringBuilder name = sb;
		};

		DirectFieldAccessor dfa = createAccessor(target);
		assertEquals(StringBuilder.class, dfa.getPropertyType("name"));
		assertEquals(sb, dfa.getPropertyValue("name"));
	}

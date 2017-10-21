	@Test
	public void testWithInlineScriptWithLeadingWhitespace() throws Exception {
		try {
			new ClassPathXmlApplicationContext("lwspBadGroovyContext.xml", getClass());
			fail("Must have thrown a BeanCreationException ('inline:' prefix was preceded by whitespace");
		}
		catch (BeanCreationException expected) {
			assertTrue(expected.contains(FileNotFoundException.class));
		}
	}

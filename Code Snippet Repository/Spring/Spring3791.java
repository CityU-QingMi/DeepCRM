	@Test
	public void testLookupWithExpectedTypeAndMatch() throws Exception {
		JndiObjectFactoryBean jof = new JndiObjectFactoryBean();
		String s = "";
		jof.setJndiTemplate(new ExpectedLookupTemplate("foo", s));
		jof.setJndiName("foo");
		jof.setExpectedType(String.class);
		jof.afterPropertiesSet();
		assertTrue(jof.getObject() == s);
	}

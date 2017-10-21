	@Test
	public void testLookupWithExpectedTypeAndNoMatch() throws Exception {
		JndiObjectFactoryBean jof = new JndiObjectFactoryBean();
		jof.setJndiTemplate(new ExpectedLookupTemplate("foo", new Object()));
		jof.setJndiName("foo");
		jof.setExpectedType(String.class);
		try {
			jof.afterPropertiesSet();
			fail("Should have thrown NamingException");
		}
		catch (NamingException ex) {
			assertTrue(ex.getMessage().contains("java.lang.String"));
		}
	}

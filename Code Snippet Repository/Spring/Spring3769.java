	@Test
	public void testLookupWithDefaultObject() throws Exception {
		JndiObjectFactoryBean jof = new JndiObjectFactoryBean();
		jof.setJndiTemplate(new ExpectedLookupTemplate("foo", ""));
		jof.setJndiName("myFoo");
		jof.setExpectedType(String.class);
		jof.setDefaultObject("myString");
		jof.afterPropertiesSet();
		assertEquals("myString", jof.getObject());
	}

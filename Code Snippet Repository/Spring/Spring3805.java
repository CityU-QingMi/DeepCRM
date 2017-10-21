	@Test
	public void testCreateInitialContext() throws Exception {
		SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
		String name = "foo";
		Object o = new Object();
		builder.bind(name, o);
		// Check it affects JNDI
		Context ctx = new InitialContext();
		assertTrue(ctx.lookup(name) == o);
		// Check it returns mutable contexts
		ctx.unbind(name);
		try {
			ctx = new InitialContext();
			ctx.lookup(name);
			fail("Should have thrown NamingException");
		}
		catch (NamingException ex) {
			// expected
		}

		// Check the same call will work again, but the context is empty
		builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
		try {
			ctx = new InitialContext();
			ctx.lookup(name);
			fail("Should have thrown NamingException");
		}
		catch (NamingException ex) {
			// expected
		}
		Object o2 = new Object();
		builder.bind(name, o2);
		assertEquals(ctx.lookup(name), o2);
	}

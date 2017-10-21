	@Test
	public void resolve() throws Exception {
		MethodParameter param = initMethodParameter(0);
		try {
			testResolveArgument(param);
			fail("Should be required by default");
		}
		catch (ServletRequestBindingException ex) {
			assertTrue(ex.getMessage().startsWith("Missing "));
		}

		Foo foo = new Foo();
		this.webRequest.setAttribute("foo", foo, getScope());
		assertSame(foo, testResolveArgument(param));
	}

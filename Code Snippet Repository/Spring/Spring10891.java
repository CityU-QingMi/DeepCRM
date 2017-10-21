	@Test
	public void cannotResolveArg() throws Exception {
		try {
			handlerMethod.invokeForRequest(webRequest, null);
			fail("Expected exception");
		}
		catch (IllegalStateException ex) {
			assertTrue(ex.getMessage().contains("No suitable resolver for argument 0 of type 'java.lang.Integer'"));
		}
	}

	@Test
	public void noMatchingResolver() throws Exception {
		Method method = on(TestController.class).mockCall(o -> o.singleArg(null)).method();
		Mono<HandlerResult> mono = invoke(new TestController(), method);

		try {
			mono.block();
			fail("Expected IllegalStateException");
		}
		catch (IllegalStateException ex) {
			assertThat(ex.getMessage(), is("No suitable resolver for argument 0 of type 'java.lang.String' " +
					"on " + method.toGenericString()));
		}
	}

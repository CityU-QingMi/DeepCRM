	@Test
	public void resolverThrowsException() throws Exception {
		Mono<Object> resolvedValue = Mono.error(new UnsupportedMediaTypeStatusException("boo"));
		Method method = on(TestController.class).mockCall(o -> o.singleArg(null)).method();
		Mono<HandlerResult> mono = invoke(new TestController(), method, resolverFor(resolvedValue));

		try {
			mono.block();
			fail("Expected UnsupportedMediaTypeStatusException");
		}
		catch (UnsupportedMediaTypeStatusException ex) {
			assertThat(ex.getMessage(), is("Response status 415 with reason \"boo\""));
		}
	}

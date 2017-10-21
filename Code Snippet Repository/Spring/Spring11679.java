	@Test
	public void illegalArgumentExceptionIsWrappedWithInvocationDetails() throws Exception {
		Mono<Object> resolvedValue = Mono.just(1);
		Method method = on(TestController.class).mockCall(o -> o.singleArg(null)).method();
		Mono<HandlerResult> mono = invoke(new TestController(), method, resolverFor(resolvedValue));

		try {
			mono.block();
			fail("Expected IllegalStateException");
		}
		catch (IllegalStateException ex) {
			assertThat(ex.getMessage(), is("Failed to invoke handler method with resolved arguments: " +
					"[0][type=java.lang.Integer][value=1] " +
					"on " + method.toGenericString()));
		}
	}

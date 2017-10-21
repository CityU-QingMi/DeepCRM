	@Test
	public void supports() throws Exception {

		testSupports(on(Handler.class).annotPresent(ModelAttribute.class).resolveReturnType(String.class));
		testSupports(on(Handler.class).annotNotPresent(ModelAttribute.class).resolveReturnType(String.class));
		testSupports(on(Handler.class).resolveReturnType(Mono.class, String.class));

		testSupports(on(Handler.class).resolveReturnType(Rendering.class));
		testSupports(on(Handler.class).resolveReturnType(Mono.class, Rendering.class));

		testSupports(on(Handler.class).resolveReturnType(View.class));
		testSupports(on(Handler.class).resolveReturnType(Mono.class, View.class));

		testSupports(on(Handler.class).resolveReturnType(void.class));
		testSupports(on(Handler.class).resolveReturnType(Mono.class, Void.class));
		testSupports(on(Handler.class).resolveReturnType(Completable.class));

		testSupports(on(Handler.class).resolveReturnType(Model.class));

		testSupports(on(Handler.class).annotPresent(ModelAttribute.class).resolveReturnType(Map.class));
		testSupports(on(Handler.class).annotNotPresent(ModelAttribute.class).resolveReturnType(Map.class));

		testSupports(on(Handler.class).resolveReturnType(TestBean.class));

		testSupports(on(Handler.class).annotPresent(ModelAttribute.class).resolveReturnType(Long.class));
		testDoesNotSupport(on(Handler.class).annotNotPresent(ModelAttribute.class).resolveReturnType(Long.class));

		// SPR-15464
		testSupports(on(Handler.class).resolveReturnType(Mono.class));
	}

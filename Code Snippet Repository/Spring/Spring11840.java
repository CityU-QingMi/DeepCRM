	@Test
	public void supportsRestController() throws NoSuchMethodException {
		Object controller = new TestRestController();
		Method method;

		method = on(TestRestController.class).returning(String.class).resolveMethod();
		testSupports(controller, method);

		method = on(TestRestController.class).returning(Mono.class, String.class).resolveMethod();
		testSupports(controller, method);

		method = on(TestRestController.class).returning(Single.class, String.class).resolveMethod();
		testSupports(controller, method);

		method = on(TestRestController.class).returning(Completable.class).resolveMethod();
		testSupports(controller, method);
	}

	@Test
	public void listNoFactoryMethod() {
		A a = new A();
		C c = new C(-50);
		B b = new B();

		List<?> items = Arrays.asList(a, c, b);
		Collections.sort(items, comparator.withSourceProvider(obj -> null));
		assertOrder(items, c, a, b);
	}

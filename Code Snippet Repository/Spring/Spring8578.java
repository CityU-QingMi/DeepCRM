	private static void assertSetFieldAndGetFieldBehaviorForProxy(Person proxy, Person target) {
		assertSetFieldAndGetFieldBehavior(proxy);

		// Get directly from Target
		assertEquals("ID (private field in a superclass)", 99, target.getId());
		assertEquals("name (protected field)", "Tom", target.getName());
		assertEquals("age (private field)", 42, target.getAge());
		assertEquals("eye color (package private field)", "blue", target.getEyeColor());
		assertEquals("'likes pets' flag (package private boolean field)", true, target.likesPets());
		assertEquals("'favorite number' (package field)", PI, target.getFavoriteNumber());
	}

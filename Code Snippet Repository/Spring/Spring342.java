	@Test
	public void testToStringDoesntHitTarget() throws Throwable {
		Object target = new TestBean() {
			@Override
			public String toString() {
				throw new UnsupportedOperationException("toString");
			}
		};
		List<Object> is = new LinkedList<>();

		Method m = Object.class.getMethod("hashCode");
		Object proxy = new Object();
		ReflectiveMethodInvocation invocation =
			new ReflectiveMethodInvocation(proxy, target, m, null, null, is);

		// If it hits target, the test will fail with the UnsupportedOpException
		// in the inner class above.
		invocation.toString();
	}

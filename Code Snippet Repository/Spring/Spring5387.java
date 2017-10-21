	@Test
	public void testGetMethodIfAvailable() throws Exception {
		Method method = ClassUtils.getMethodIfAvailable(Collection.class, "size");
		assertNotNull(method);
		assertEquals("size", method.getName());

		method = ClassUtils.getMethodIfAvailable(Collection.class, "remove", new Class[] {Object.class});
		assertNotNull(method);
		assertEquals("remove", method.getName());

		assertNull(ClassUtils.getMethodIfAvailable(Collection.class, "remove"));
		assertNull(ClassUtils.getMethodIfAvailable(Collection.class, "someOtherMethod"));
	}

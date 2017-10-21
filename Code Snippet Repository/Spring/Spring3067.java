	public void testCheckedThrowable(CacheableService<?> service) throws Exception {
		String arg = UUID.randomUUID().toString();
		try {
			service.throwChecked(arg);
			fail("Excepted exception");
		}
		catch (Exception ex) {
			assertEquals("Wrong exception type", IOException.class, ex.getClass());
			assertEquals(arg, ex.getMessage());
		}
	}

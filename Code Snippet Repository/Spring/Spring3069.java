	public void testCheckedThrowableSync(CacheableService<?> service) throws Exception {
		String arg = UUID.randomUUID().toString();
		try {
			service.throwCheckedSync(arg);
			fail("Excepted exception");
		}
		catch (Exception ex) {
			ex.printStackTrace();
			assertEquals("Wrong exception type", IOException.class, ex.getClass());
			assertEquals(arg, ex.getMessage());
		}
	}

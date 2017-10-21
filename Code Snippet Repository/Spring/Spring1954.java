	@Test
	public void customInterceptorAppliesWithCheckedException() {
		try {
			cs.cacheWithCheckedException("id", true);
			fail("Should have failed");
		}
		catch (RuntimeException e) {
			assertNotNull("missing original exception", e.getCause());
			assertEquals(IOException.class, e.getCause().getClass());
		}
		catch (Exception e) {
			fail("Wrong exception type " + e);
		}
	}

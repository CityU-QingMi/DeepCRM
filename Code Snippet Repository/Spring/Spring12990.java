	@Test
	public void resolveRequestPartNotValid() throws Exception {
		try {
			testResolveArgument(new SimpleBean(null), paramValidRequestPart);
			fail("Expected exception");
		}
		catch (MethodArgumentNotValidException ex) {
			assertEquals("requestPart", ex.getBindingResult().getObjectName());
			assertEquals(1, ex.getBindingResult().getErrorCount());
			assertNotNull(ex.getBindingResult().getFieldError("name"));
		}
	}

	@Test
	public void resolveRequestPartRequired() throws Exception {
		try {
			testResolveArgument(null, paramValidRequestPart);
			fail("Expected exception");
		}
		catch (MissingServletRequestPartException ex) {
			assertEquals("requestPart", ex.getRequestPartName());
		}
	}

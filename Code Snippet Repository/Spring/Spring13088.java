	@Test
	public void equivalentMappingsWithSameMethodName() throws Exception {
		try {
			initServletWithControllers(ChildController.class);
			fail("Expected 'method already mapped' error");
		}
		catch (BeanCreationException e) {
			assertTrue(e.getCause() instanceof IllegalStateException);
			assertTrue(e.getCause().getMessage().contains("Ambiguous mapping"));
		}
	}

	@Test
	public void testEntityManagerProxyRejectsProgrammaticTxManagement() {
		try {
			createContainerManagedEntityManager().getTransaction();
			fail("Should have thrown an IllegalStateException");
		}
		catch (IllegalStateException ex) {
			// expected
		}
	}

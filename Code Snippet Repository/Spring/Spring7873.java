	@Test
	public void testBogusQuery() {
		try {
			Query query = sharedEntityManager.createQuery("It's raining toads");
			// required in OpenJPA case
			query.executeUpdate();
			fail("Should have thrown a RuntimeException");
		}
		catch (RuntimeException ex) {
			// expected
		}
	}

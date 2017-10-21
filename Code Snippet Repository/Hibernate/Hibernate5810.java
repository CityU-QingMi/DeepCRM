	@Test
	public void testWrongIdType() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		try {
			em.getReference( Competitor.class, "30" );
			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			//success
		}
		catch ( Exception e ) {
			fail("Wrong exception: " + e );
		}

		try {
			em.getReference( Mail.class, 1 );
			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			//success
		}
		catch ( Exception e ) {
			fail("Wrong exception: " + e );
		}
		em.close();
	}

	@Test
	public void testEntityNotFoundException() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		Music music = em.getReference( Music.class, -1 );
		try {
			music.getName();
			fail( "Non existent entity should raise an exception when state is accessed" );
		}
		catch ( EntityNotFoundException e ) {
            log.debug("success");
		}
		finally {
			em.close();
		}
	}

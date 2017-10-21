	@Test
	public void testException() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Rythm r = new Rythm();
		try {
			em.persist( r );
			em.flush();
			fail("should have raised an ArythmeticException:");
		}
		catch (ArithmeticException e) {
			//success
		}
		catch( Exception e ) {
			fail("should have raised an ArythmeticException:" + e.getClass() );
		}

		em.getTransaction().rollback();
		em.close();

	}

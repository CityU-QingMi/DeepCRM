	public void listenerAnnotation() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		Translation tl = new Translation();
		em.getTransaction().begin();
		tl.setInto( "France" );
		em.persist( tl );
		tl = new Translation();
		tl.setInto( "Bimboland" );
		try {
			em.persist( tl );
			em.flush();
			fail( "Annotations annotated by a listener not used" );
		}
		catch (IllegalArgumentException e) {
			//success
		}
		finally {
			em.getTransaction().rollback();
			em.close();
		}
	}

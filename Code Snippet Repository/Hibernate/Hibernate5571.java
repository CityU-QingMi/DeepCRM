	@Test
	public void testMergeWithTransientNonCascadedAssociation() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Person person = new Person();
		em.persist( person );
		em.getTransaction().commit();
		em.close();

		person.address = new Address();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.merge( person );
		try {
			em.flush();
			fail( "Expecting IllegalStateException" );
		}
		catch (IllegalStateException ise) {
			// expected...
			em.getTransaction().rollback();
		}
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		person.address = null;
		em.unwrap( Session.class ).lock( person, LockMode.NONE );
		em.unwrap( Session.class ).delete( person );
		em.getTransaction().commit();
		em.close();
	}

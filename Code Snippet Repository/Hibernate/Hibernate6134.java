	@Test
	public void shouldApplyConfiguredTypeForProjectionOfScalarValue() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( new Person( 1, 29 ) );
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		List<String> results = em.createNamedQuery( "personAge", String.class ).getResultList();
		assertEquals( 1, results.size() );
		assertEquals( "29", results.get( 0 ) );
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.createQuery( "delete from Person" ).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

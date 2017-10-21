	@Test
	public void testJpqlLiteralBetween() {
		final EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		@SuppressWarnings("unchecked")
		final List<Employee> result0 = em.createQuery( "from Employee where salary between 300.0F and 400.0F" ).getResultList();
		assertEquals( 0, result0.size() );

		@SuppressWarnings("unchecked")
		final List<Employee> result1 = em.createQuery( "from Employee where salary between 100.0F and 200.0F" ).getResultList();
		assertEquals( 0, result1.size() );

		@SuppressWarnings("unchecked")
		final List<Employee> result2 = em.createQuery( "from Employee where salary between 200.0F and 300.0F" ).getResultList();
		assertEquals( 1, result2.size() );

		em.getTransaction().commit();
		em.close();
	}

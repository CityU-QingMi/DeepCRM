	@Test
	@TestForIssue( jiraKey = "" )
	public void testCriteriaBetween() {
		final EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Employee> query = cb.createQuery( Employee.class );
		final Root<Employee> root = query.from( Employee.class );
		query.select( root );

		query.where( cb.between( root.<Float>get( "salary" ), new Float( 300f ), new Float( 400f ) ) );
		final List<Employee> result0 = em.createQuery( query ).getResultList();
		assertEquals( 0, result0.size() );

		query.where( cb.between( root.<Float>get( "salary" ), new Float( 100f ), new Float( 200f ) ) );
		final List<Employee> result1 = em.createQuery( query ).getResultList();
		assertEquals( 0, result1.size() );

		query.where( cb.between( root.<Float>get( "salary" ), new Float( 200f ), new Float( 300f ) ));
		final List<Employee> result2 = em.createQuery( query ).getResultList();
		assertEquals( 1, result2.size() );

		em.getTransaction().commit();
		em.close();
	}

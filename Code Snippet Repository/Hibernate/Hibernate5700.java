	@Test
	@TestForIssue( jiraKey = "" )
	public void testParameterizedFunctions() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		// lower
		CriteriaQuery<Client> cq = cb.createQuery( Client.class );
		Root<Client> root = cq.from( Client.class );
		cq.where( cb.equal( cb.lower( root.get( Client_.name ).get( Name_.lastName ) ),"test" ) );
		em.createQuery( cq ).getResultList();
		// upper
		cq = cb.createQuery( Client.class );
		root = cq.from( Client.class );
		cq.where( cb.equal( cb.upper( root.get( Client_.name ).get( Name_.lastName ) ),"test" ) );
		em.createQuery( cq ).getResultList();
		em.getTransaction().commit();
		em.close();
	}

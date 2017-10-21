	@Test
	public void testUsingSimpleHbmInJpa(){
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Qualifier> cq = cb.createQuery(Qualifier.class);
		Root<Qualifier> qualifRoot = cq.from(Qualifier.class);
		cq.where( cb.equal( qualifRoot.get( "qualifierId" ), 32l ) );
		em.createQuery(cq).getResultList();
		em.getTransaction().commit();
		em.close();
	}

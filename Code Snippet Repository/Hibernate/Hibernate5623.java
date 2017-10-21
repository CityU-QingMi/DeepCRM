	@RequiresDialect(value = H2Dialect.class, jiraKey = "")
	public void testMultiselectWithPredicates() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		CriteriaBuilderImpl cb = (CriteriaBuilderImpl) em.getCriteriaBuilder();
		CriteriaQuery<Customer> cq = cb.createQuery( Customer.class );
		Root<Customer> r = cq.from( Customer.class );
		cq.multiselect(
				r.get( Customer_.id ), r.get( Customer_.name ),
				cb.concat( "Hello ", r.get( Customer_.name ) ), cb.isNotNull( r.get( Customer_.age ) )
		);
		TypedQuery<Customer> tq = em.createQuery( cq );
		tq.getResultList();

		em.getTransaction().commit();
		em.close();
	}

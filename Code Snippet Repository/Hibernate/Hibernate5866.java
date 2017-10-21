	private TypedQuery<User> createTypedQuery(EntityManager em) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery( User.class );
		Root<User> root = cq.from( User.class );

		cq.where( cb.equal( root.get( "id" ), 1L ) );
		TypedQuery<User> tq = em.createQuery( cq );
		tq.setHint( "javax.persistence.loadgraph", createEntityGraph( em ) );
		return tq;
	}

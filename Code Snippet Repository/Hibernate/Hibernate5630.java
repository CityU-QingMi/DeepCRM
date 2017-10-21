	private List<Object> find(Class<?> clazz, int id, String path) {
		EntityManager em = createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery();
		Root<?> root = cq.from( clazz );

		cq.select( root.get( path ) )
				.where( cb.equal( root.get( "id" ), id ) );

		TypedQuery<Object> query = em.createQuery( cq );
		return query.getResultList();
	}

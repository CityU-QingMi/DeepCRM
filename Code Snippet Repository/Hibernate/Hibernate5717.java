	@Test
	@TestForIssue(jiraKey = "")
	public void testQueryEnumCollection() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		User user = new User();
		user.setId( 1l );
		user.getRoles().add( User.Role.Admin );
		em.persist( user );
		em.getTransaction().commit();
		em.getTransaction().begin();

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<User> query = builder.createQuery( User.class );
		Root<User> root = query.from( User.class );

		Expression<Set<User.Role>> roles = root.get( User_.roles );

		// Using the correct collection of enums and an enum parameter
		query.where( builder.isMember( User.Role.Admin, roles ) );

		TypedQuery<User> typedQuery = em.createQuery( query );
		List<User> users = typedQuery.getResultList();
		assertEquals( 1, users.size() );

		em.getTransaction().commit();
		em.getTransaction().begin();
		// delete
		em.remove( user );
		em.getTransaction().commit();

	}

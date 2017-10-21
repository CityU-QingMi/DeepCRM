	@Test
	@TestForIssue(jiraKey = "")
	public void testCaseLiteralResult() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Boolean> cq = cb.createQuery( Boolean.class );
		Root<Customer> expense_ = cq.from( Customer.class );
		em.createQuery(
				cq.distinct( true ).where(
						cb.equal( expense_.get( "email" ), "@hibernate.com" )
				).multiselect(
						cb.selectCase()
								.when( cb.gt( cb.count( expense_ ), cb.literal( 0L ) ), cb.literal( true ) )
								.otherwise( cb.literal( false ) )
				)
		).getSingleResult();
	}

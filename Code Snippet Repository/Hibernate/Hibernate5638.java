	@Test
	@TestForIssue(jiraKey = "")
	public void testJoinOnTreatedJoin() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Bid> criteria = cb.createQuery(Bid.class);
			Root<Bid> root = criteria.from(Bid.class);
			Join<Book, Author> join = cb.treat(
					root.<Bid, Item> join("item"), Book.class)
					.join("author");
			criteria.where(cb.equal(join.<String> get("name"), "Andrea Camilleri"));
			entityManager.createQuery(criteria.select(root)).getResultList();
		} );
	}

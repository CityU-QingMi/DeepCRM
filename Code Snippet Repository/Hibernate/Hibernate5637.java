	@Test
	@TestForIssue(jiraKey = "")
	public void testJoinOnTreatedRootWithJoin() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Item> criteria = cb.createQuery(Item.class);
			Root<Item> root = criteria.from(Item.class);
			root.join( "price" );
			Root<Book> treatedRoot =  cb.treat(root, Book.class);
			criteria.where(
					cb.equal(
							treatedRoot.<Book, Author>join("author").<String>get("name"),
							"Andrea Camilleri"));
			entityManager.createQuery(criteria.select(treatedRoot)).getResultList();
		} );
	}

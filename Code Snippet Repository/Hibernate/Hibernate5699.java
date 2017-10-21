	@Test
	public void testEmbeddableInPath() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Client client = new Client( 111, "steve", "ebersole" );
		em.persist(client);
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Client> cq = cb.createQuery(Client.class);
		Root<Client> root = cq.from(Client.class);
		cq.where(cb.equal(root.get("name").get("firstName"), client.getName().getFirstName()));
		List<Client> list = em.createQuery(cq).getResultList();
		Assert.assertEquals( 1, list.size() );
		em.getTransaction().commit();
		em.close();
		
		// HHH-5792
		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		TypedQuery< Client > q = em.createQuery(
				"SELECT c FROM Client c JOIN c.name n WHERE n.firstName = '"
						+ client.getName().getFirstName() + "'",
                 Client.class );
		Assert.assertEquals( 1, q.getResultList().size() );
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.createQuery( "delete Client" ).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

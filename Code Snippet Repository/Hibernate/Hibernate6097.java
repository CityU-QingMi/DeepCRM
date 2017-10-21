	@Test
	public void testAggregationReturnType() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			Item item = new Item( "Mouse", "Micro$oft mouse" );
			em.persist( item );
			item = new Item( "Computer", "Apple II" );
			em.persist( item );
			Query q = em.createQuery( "select count(i) from Item i where i.name like :itemName" );
			q.setParameter( "itemName", "%" );
			assertTrue( q.getSingleResult() instanceof Long );
		}
		finally {
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

	@Test
	public void testPagedQuery() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			Item item = new Item( "Mouse", "Micro$oft mouse" );
			em.persist( item );
			item = new Item( "Computer", "Apple II" );
			em.persist( item );
			Query q = em.createQuery( "select i from " + Item.class.getName() + " i where i.name like :itemName" );
			q.setParameter( "itemName", "%" );
			q.setMaxResults( 1 );
			q.getSingleResult();
			q = em.createQuery( "select i from Item i where i.name like :itemName" );
			q.setParameter( "itemName", "%" );
			q.setFirstResult( 1 );
			q.setMaxResults( 1 );
		}
		finally {
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

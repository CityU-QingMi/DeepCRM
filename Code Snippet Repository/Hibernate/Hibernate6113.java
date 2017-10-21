	@Test
	public void testIsNull() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			Distributor d1 = new Distributor();
			d1.setName( "Fnac" );
			Distributor d2 = new Distributor();
			d2.setName( "Darty" );
			Item item = new Item( "Mouse", null );
			Item item2 = new Item( "Mouse2", "dd" );
			item.getDistributors().add( d1 );
			item.getDistributors().add( d2 );
			em.persist( d1 );
			em.persist( d2 );
			em.persist( item );
			em.persist( item2 );
			em.flush();
			em.clear();
			Query q = em.createQuery(
					"select i from Item i where i.descr = :descr or (i.descr is null and cast(:descr as string) is null)"
			);
			//Query q = em.createQuery( "select i from Item i where (i.descr is null and :descr is null) or (i.descr = :descr");
			q.setParameter( "descr", "dd" );
			List result = q.getResultList();
			assertEquals( 1, result.size() );
			q.setParameter( "descr", null );
			result = q.getResultList();
			assertEquals( 1, result.size() );
			//item = (Item) distinctResult.get( 0 );
		}
		finally {
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

	@Test
	public void testDistinct() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			em.createQuery( "delete Item" ).executeUpdate();
			em.createQuery( "delete Distributor" ).executeUpdate();
			Distributor d1 = new Distributor();
			d1.setName( "Fnac" );
			Distributor d2 = new Distributor();
			d2.setName( "Darty" );
			Item item = new Item( "Mouse", "Micro$oft mouse" );
			item.getDistributors().add( d1 );
			item.getDistributors().add( d2 );
			em.persist( d1 );
			em.persist( d2 );
			em.persist( item );
			em.flush();
			em.clear();
			Query q = em.createQuery( "select distinct i from Item i left join fetch i.distributors" );
			item = (Item) q.getSingleResult()
			;
			//assertEquals( 1, distinctResult.size() );
			//item = (Item) distinctResult.get( 0 );
			assertTrue( Hibernate.isInitialized( item.getDistributors() ) );
			assertEquals( 2, item.getDistributors().size() );
		}
		finally {
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

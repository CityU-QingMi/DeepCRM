	@Test
	public void testTypedScalarQueries() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			Item item = new Item( "Mouse", "Micro$oft mouse" );
			em.persist( item );
			assertTrue( em.contains( item ) );
			em.getTransaction().commit();

			em.getTransaction().begin();
			Object[] itemData = em.createQuery( "select i.name,i.descr from Item i", Object[].class ).getSingleResult();
			assertEquals( 2, itemData.length );
			assertEquals( String.class, itemData[0].getClass() );
			assertEquals( String.class, itemData[1].getClass() );
			Tuple itemTuple = em.createQuery( "select i.name,i.descr from Item i", Tuple.class ).getSingleResult();
			assertEquals( 2, itemTuple.getElements().size() );
			assertEquals( String.class, itemTuple.get( 0 ).getClass() );
			assertEquals( String.class, itemTuple.get( 1 ).getClass() );
			Item itemView = em.createQuery( "select new Item(i.name,i.descr) from Item i", Item.class )
					.getSingleResult();
			assertNotNull( itemView );
			assertEquals( "Micro$oft mouse", itemView.getDescr() );
			itemView = em.createNamedQuery( "query-construct", Item.class ).getSingleResult();
			assertNotNull( itemView );
			assertEquals( "Micro$oft mouse", itemView.getDescr() );
			em.remove( item );
			em.getTransaction().commit();
		}
		catch (Exception e) {
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			em.close();
		}
	}

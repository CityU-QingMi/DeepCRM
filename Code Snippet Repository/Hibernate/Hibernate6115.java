	@Test
	public void testUnavailableNamedQuery() throws Exception {
		Item item = new Item( "Mouse", "Micro$oft mouse" );

		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			em.persist( item );
			try {
				em.createNamedQuery( "wrong name" );
				fail( "Wrong named query should raise an exception" );
			}
			catch (IllegalArgumentException e) {
				//success
			}
			assertTrue(
					"thrown IllegalArgumentException should of caused transaction to be marked for rollback only",
					true == em.getTransaction().getRollbackOnly()
			);
			em.getTransaction().rollback();        // HHH-8442 changed to rollback since thrown ISE causes
			// transaction to be marked for rollback only.
			// No need to remove entity since it was rolled back.

			assertNull(
					"entity should not of been saved to database since IllegalArgumentException should of" +
							"caused transaction to be marked for rollback only", em.find( Item.class, item.getName() )
			);
		}
		finally {
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

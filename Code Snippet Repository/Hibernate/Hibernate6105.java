	@Test
	@TestForIssue(jiraKey = "")
	public void testTypedManipulationQueryError() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		try {
			em.createQuery( "delete Item", Item.class );
			fail();
		}
		catch (IllegalArgumentException expected) {
			//expected
		}
		finally {
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
		}

		try {
			em.createQuery( "update Item i set i.name = 'someName'", Item.class );
			fail();
		}
		catch (IllegalArgumentException expected) {
			//expected
		}
		finally {
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

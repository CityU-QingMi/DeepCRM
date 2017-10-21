	@Test
	@TestForIssue(jiraKey = "")
	public void testFlushTransientOneToOneNoCascade() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		B b = new B();
		A a = new A();

		a.setB(b);
		try {
			em.persist( a );
			em.flush();
			em.getTransaction().commit();
			fail( "should have raised an IllegalStateException" );
		}
		catch (IllegalStateException ex) {
			if ( em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			// IllegalStateException caught as expected
		}
		finally {
			em.close();
		}
	}

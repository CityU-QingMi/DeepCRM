	@Test
	public void testEntityNotFoundException() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Wallet w = new Wallet();
		w.setBrand("Lacoste");
		w.setModel("Minimic");
		w.setSerial("0324");
		em.persist(w);
		Wallet wallet = em.find( Wallet.class, w.getSerial() );
		em.createNativeQuery("delete from Wallet").executeUpdate();
		try {
			em.refresh(wallet);
		} catch (EntityNotFoundException enfe) {
			// success
			if (em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
			em.close();
			return;
		}

		try {
			em.getTransaction().commit();
			fail("Should have raised an EntityNotFoundException");
		} catch (PersistenceException pe) {
		} finally {
			em.close();
		}
	}

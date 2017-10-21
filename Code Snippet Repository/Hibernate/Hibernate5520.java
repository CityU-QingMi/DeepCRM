	@Test
	public void testClear() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Wallet w = new Wallet();
		w.setBrand( "Lacoste" );
		w.setModel( "Minimic" );
		w.setSerial( "0100202002" );
		em.persist( w );
		em.flush();
		em.clear();
		assertFalse( em.contains( w ) );
		em.getTransaction().rollback();
		em.close();
	}

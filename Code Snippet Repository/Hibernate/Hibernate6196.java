	@Test
	public void testSimpleId() {
		EntityManager em = entityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Book book = new Book();
			em.persist( book );
			em.flush();
			assertEquals( book.getId(), em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier( book ) );
		}
		finally {
			em.getTransaction().rollback();
			em.close();
		}
	}

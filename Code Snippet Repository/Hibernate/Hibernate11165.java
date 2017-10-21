	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		Staff staff = new Staff( HEIGHT_INCHES, 1 );
		em.persist( staff );
		em.getTransaction().commit();
		id = staff.getId();
	}

	@Before
	public void init() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		Document a = new Document();
		a.getTags().add("important");
		a.getTags().add("business");
		em.persist(a);
		docId = a.getId();

		em.getTransaction().commit();
		em.close();
	}

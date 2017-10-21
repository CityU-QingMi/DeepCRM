	@Before
	public void before() {
		EntityManager entityManager = entityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		ManyToOneType manyToOneType = new ManyToOneType( THEVALUE );
		EmbeddedType embeddedType = new EmbeddedType( manyToOneType );
		Entity entity = new Entity( embeddedType );
		entityManager.persist( entity );
		entityManager.getTransaction().commit();
		entityManager.close();
	}

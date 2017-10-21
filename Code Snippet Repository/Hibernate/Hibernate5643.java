	@Test
	@TestForIssue( jiraKey = "" )
	public void treatRoot() {
		EntityManager em = getOrCreateEntityManager();

		em.getTransaction().begin();
		Animal animal = new Animal();
		animal.setId(100L);
		animal.setName("2");
		em.persist(animal);
		Human human = new Human();
		human.setId(200L);
		human.setName("2");
		em.persist(human);
		Elephant elephant = new Elephant();
		elephant.setId( 300L );
		elephant.setName( "2" );
		em.persist( elephant );
		em.getTransaction().commit();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Human> criteria = builder.createQuery( Human.class );
		Root<Animal> root = criteria.from( Animal.class );
		criteria.select( builder.treat( root, Human.class ) );
		List<Human> humans = em.createQuery( criteria ).getResultList();
		Assert.assertEquals( 1, humans.size() );

		em.close();
	}

	@Test
	@TestForIssue( jiraKey = "" )
	public void treatRootReturnSuperclass() {
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
		CriteriaQuery<Animal> criteria = builder.createQuery( Animal.class );
		Root<Animal> root = criteria.from( Animal.class );
		criteria.select( builder.treat( root, Human.class ) );
		List<Animal> animalsThatAreHuman = em.createQuery( criteria ).getResultList();
		Assert.assertEquals( 1, animalsThatAreHuman.size() );
		Assert.assertTrue( Human.class.isInstance( animalsThatAreHuman.get( 0 ) ) );

		em.close();
	}

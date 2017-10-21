	@Test
	public void treatPathClassTest() {
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
		CriteriaQuery<String> criteria = builder.createQuery( String.class );
		Root<Animal> root = criteria.from( Animal.class );
		EntityType<Animal> Animal_ = em.getMetamodel().entity(Animal.class);
		criteria.select(root.get(Animal_.getSingularAttribute("name", String.class)));

		criteria.where(builder.like(builder.treat(root, Human.class).get( org.hibernate.jpa.test.criteria.Human_.name ), "2%"));
		List<String> animalList = em.createQuery( criteria ).getResultList();
		Assert.assertEquals("treat(Animal as Human) was ignored",1, animalList.size());

		CriteriaQuery<Long> idCriteria = builder.createQuery( Long.class );
		Root<Animal> idRoot = idCriteria.from( Animal.class );
		idCriteria.select( idRoot.get( Animal_.getSingularAttribute( "id", Long.class ) ) );

		idCriteria.where(
				builder.like(
						builder.treat( idRoot, Human.class )
								.get( org.hibernate.jpa.test.criteria.Human_.name ), "2%"
				)
		);
		List<Long> animalIdList = em.createQuery( idCriteria ).getResultList();
		Assert.assertEquals( "treat(Animal as Human) was ignored", 1, animalIdList.size() );
		Assert.assertEquals( 200L, animalIdList.get( 0 ).longValue() );

		em.close();
	}

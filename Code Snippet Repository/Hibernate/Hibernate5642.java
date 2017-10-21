	@Test
	public void treatPathClassTestHqlControl() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Animal animal = new Animal();
		animal.setId(100L);
		animal.setName("2");
		em.persist( animal );
		Human human = new Human();
		human.setId(200L);
		human.setName("2");
		em.persist(human);
		Elephant elephant = new Elephant();
		elephant.setId( 300L );
		elephant.setName( "2" );
		em.persist( elephant );
		em.getTransaction().commit();

		List<String> animalList = em.createQuery( "select a.name from Animal a where treat (a as Human).name like '2%'" ).getResultList();
		Assert.assertEquals( "treat(Animal as Human) was ignored", 1, animalList.size() );

		List<Long> animalIdList = em.createQuery( "select a.id from Animal a where treat (a as Human).name like '2%'" ).getResultList();
		Assert.assertEquals("treat(Animal as Human) was ignored",1, animalList.size());
		Assert.assertEquals( 200L, animalIdList.get( 0 ).longValue() );

		em.close();
	}

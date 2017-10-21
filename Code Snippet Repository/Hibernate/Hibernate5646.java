	@Test
	@TestForIssue(jiraKey = "")
	public void testTreatWithRestrictionOnAbstractClass() {
		EntityManager em = getOrCreateEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();

		Greyhound greyhound = new Greyhound();
		Dachshund dachshund = new Dachshund();
		em.persist( greyhound );
		em.persist( dachshund );

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TreatAnimal> criteriaQuery = cb.createQuery( TreatAnimal.class );

		Root<TreatAnimal> animal = criteriaQuery.from( TreatAnimal.class );

		Root<Dog> dog = cb.treat( animal, Dog.class );

		// only fast dogs
		criteriaQuery.where( cb.isTrue( dog.<Boolean>get( "fast" ) ) );

		List<TreatAnimal> results = em.createQuery( criteriaQuery ).getResultList();

		// we should only have a single Greyhound here, not slow long dogs!
		assertEquals( Arrays.asList( greyhound ), results );

		entityTransaction.commit();
		em.close();
	}

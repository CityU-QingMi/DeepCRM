	@Test
	@TestForIssue(jiraKey = "")
	@FailureExpected( jiraKey = "" )
	public void testMissingDialectFunction() {
		doInJPA( this::entityManagerFactory, em -> {
			Human human = new Human();
			human.setId(200L);
			human.setName("2");
			human.setBorn(new Date());
			em.persist(human);

			em.getTransaction().commit();

			CriteriaBuilder cb =  em.getCriteriaBuilder();
			CriteriaQuery<HumanDTO> criteria = cb.createQuery( HumanDTO.class );
			Root<Human> root = criteria.from( Human.class );

			criteria.select(
				cb.construct(
					HumanDTO.class,
					root.get(Human_.id),
					root.get(Human_.name),
					cb.function(
						"convert",
						String.class,
						root.get(Human_.born),
						cb.literal(110)
					)
				)
			);

			em.createQuery( criteria ).getResultList();
		} );
	}

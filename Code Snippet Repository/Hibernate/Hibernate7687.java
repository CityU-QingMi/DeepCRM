	@Test
	@TestForIssue(jiraKey = "")
	public void hbmEnumWithExplicitTypeTest() {
		long id = doInHibernate( this::sessionFactory, session -> {
			Person person = Person.person(Gender.MALE, HairColor.BROWN);
			person.setOriginalHairColor(HairColor.BLONDE);
			session.persist(person);

			return person.getId();
		} );

		doInHibernate( this::sessionFactory, session -> {
			Number personId = (Number) session.createNativeQuery(
				"select id from Person where originalHairColor = :color")
			.setParameter("color", HairColor.BLONDE.name())
			.getSingleResult();

			assertEquals( id, personId.longValue() );
		} );
	}

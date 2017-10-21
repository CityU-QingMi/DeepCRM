	@Test
	@TestForIssue( jiraKey = "" )
	public void memberOfTreatTest() {
		// prepare test data
		Session s = openSession();
		s.getTransaction().begin();

		Human owner = new Human();
		s.persist( owner );

		Dog wildDog = new Dog();
		s.persist( wildDog );

		Dog petDog = new Dog();
		petDog.setOwner( owner );
		s.persist( petDog );

		Cat petCat = new Cat();
		petCat.setOwner( owner );
		s.persist( petCat );

		s.getTransaction().commit();
		s.close();


		// perform test
		s = openSession();
		s.getTransaction().begin();
		Query q = s.createQuery(
				"select pet" +
						" from Animal pet, Animal owner" +
						" where pet member of treat (owner as Human).pets"
		);
		@SuppressWarnings("unchecked")
		List<Animal> results = q.list();
		assertEquals( 2, results.size() );
		s.getTransaction().commit();
		s.close();


		// clean up test data
		s = openSession();
		s.getTransaction().begin();
		s.delete( petCat );
		s.delete( petDog );
		s.delete( wildDog );
		s.delete( owner );
		s.getTransaction().commit();
		s.close();
	}

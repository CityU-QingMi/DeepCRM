    @Test
    public void testCache() {
        Person aPerson = doInJPA( this::entityManagerFactory, entityManager -> {
            entityManager.persist( new Person() );
            entityManager.persist( new Person() );
			Person person = new Person();
            entityManager.persist( person );
			return person;
        });
		doInJPA( this::entityManagerFactory, entityManager -> {
			List<Object> dtos = new ArrayList<>(  );
			//tag::caching-management-jpa-detach-example[]
			for(Person person : entityManager.createQuery("select p from Person p", Person.class)
					.getResultList()) {
				dtos.add(toDTO(person));
				entityManager.detach( person );
			}
			//end::caching-management-jpa-detach-example[]
			//tag::caching-management-clear-example[]
			entityManager.clear();

			//end::caching-management-clear-example[]

			Person person = aPerson;

			//tag::caching-management-contains-example[]
			entityManager.contains( person );

			//end::caching-management-contains-example[]
		});
		doInJPA( this::entityManagerFactory, entityManager -> {
			List<Object> dtos = new ArrayList<>(  );
			//tag::caching-management-native-evict-example[]
			Session session = entityManager.unwrap( Session.class );
			for(Person person : (List<Person>) session.createQuery("select p from Person p").list()) {
				dtos.add(toDTO(person));
				session.evict( person );
			}
			//end::caching-management-native-evict-example[]
			//tag::caching-management-clear-example[]
			session.clear();
			//end::caching-management-clear-example[]

			Person person = aPerson;

			//tag::caching-management-contains-example[]
			session.contains( person );
			//end::caching-management-contains-example[]
		});
    }

    @Test
    public void testCache() {
        doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person();
            entityManager.persist( person );
			Phone home = new Phone( "123-456-7890" );
			Phone office = new Phone( "098-765-4321" );
			person.addPhone( home );
			person.addPhone( office );
        });
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = entityManager.find( Person.class, 1L );
			person.getPhones().size();
		});
		doInJPA( this::entityManagerFactory, entityManager -> {
			log.info( "Log collection from cache" );
			//tag::caching-collection-example[]
			Person person = entityManager.find( Person.class, 1L );
			person.getPhones().size();
			//end::caching-collection-example[]
		});
        doInJPA( this::entityManagerFactory, entityManager -> {
			log.info( "Load from cache" );
            entityManager.find( Person.class, 1L ).getPhones().size();
        });
    }

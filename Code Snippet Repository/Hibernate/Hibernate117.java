    @Test
    public void testOrder() {
        doInJPA( this::entityManagerFactory, entityManager -> {
            entityManager.createNativeQuery("delete from Person").executeUpdate();
        });
        doInJPA( this::entityManagerFactory, entityManager -> {
            Person person = new Person("John Doe");
            person.id = 1L;
            entityManager.persist(person);
        });
        doInJPA( this::entityManagerFactory, entityManager -> {
            log.info("testFlushSQL");
            //tag::flushing-order-example[]
            Person person = entityManager.find( Person.class, 1L);
            entityManager.remove(person);

            Person newPerson = new Person( );
            newPerson.setId( 2L );
            newPerson.setName( "John Doe" );
            entityManager.persist( newPerson );
            //end::flushing-order-example[]
        });
    }

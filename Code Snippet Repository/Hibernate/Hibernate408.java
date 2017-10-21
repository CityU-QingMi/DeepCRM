    @Test
    public void test() {
		//tag::schema-generation-column-default-value-persist-example[]
        doInJPA( this::entityManagerFactory, entityManager -> {
            Person person = new Person();
            person.setId( 1L );
            entityManager.persist( person );
        } );
        doInJPA( this::entityManagerFactory, entityManager -> {
            Person person = entityManager.find( Person.class, 1L );
            assertEquals( "N/A", person.getName() );
            assertEquals( Long.valueOf( -1L ), person.getClientId() );
        } );
		//end::schema-generation-column-default-value-persist-example[]
    }

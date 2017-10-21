    @Test
    public void testBasicExpectedBehavior() {

		//tag::multitenacy-multitenacy-hibernate-same-entity-example[]
        doInSession( FRONT_END_TENANT, session -> {
            Person person = new Person(  );
            person.setId( 1L );
            person.setName( "John Doe" );
            session.persist( person );
        } );

        doInSession( BACK_END_TENANT, session -> {
            Person person = new Person(  );
            person.setId( 1L );
            person.setName( "John Doe" );
            session.persist( person );
        } );
		//end::multitenacy-multitenacy-hibernate-same-entity-example[]
    }

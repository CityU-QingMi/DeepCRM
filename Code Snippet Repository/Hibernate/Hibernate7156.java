    @SuppressWarnings( "" )
    private void performQueryAndVerifyPersonResults(String query) {
        List<Person> persons;
        try ( Session s = openSession() ) {
            persons = (List<Person>) s.createQuery( query ).list();
        }
        for ( Person person : persons ) {
            assertTrue( isInitialized( person ) );
            if ( shouldHaveVehicle( person ) ) {
                assertNotNull( person.getVehicle() );
                assertTrue( isInitialized( person.getVehicle() ) );
                assertNotNull( person.getVehicle().getDriver() );
            }
        }
    }

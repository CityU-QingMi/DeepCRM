    @Test
    @SuppressWarnings( "" )
    public void test3() {
        doInHibernate( this::sessionFactory, s -> {
            List<Person> persons = (List<Person>) s.createCriteria( Person.class ).setFetchMode( "vehicle", FetchMode.JOIN ).list();
            for ( Person person : persons ) {
                if ( shouldHaveVehicle( person ) ) {
                    assertNotNull( person.getVehicle() );
                    assertNotNull( person.getVehicle().getDriver() );
                }
            }
        } );
    }

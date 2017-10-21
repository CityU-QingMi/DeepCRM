    @Test
    @SuppressWarnings( "" )
    public void test4() {
        List<Vehicle> vehicles;

        try ( Session s = openSession() ) {
            vehicles = (List<Vehicle>) s.createCriteria( Vehicle.class ).setFetchMode( "driver", FetchMode.JOIN ).list();
        }

        for ( Vehicle vehicle : vehicles ) {
            if ( shouldHaveDriver( vehicle ) ) {
                assertNotNull( vehicle.getDriver() );
                assertNotNull( vehicle.getDriver().getVehicle() );
            }
        }
    }

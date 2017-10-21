    @SuppressWarnings( "" )
    private void performQueryAndVerifyVehicleResults(String query) {
        List<Vehicle> vehicles;
        try ( Session s = openSession() ) {
            vehicles = (List<Vehicle>) s.createQuery( query ).list();
        }
        for ( Vehicle vehicle : vehicles ) {
            if ( shouldHaveDriver( vehicle ) ) {
                assertNotNull( vehicle.getDriver() );
                assertNotNull( vehicle.getDriver().getVehicle() );
            }
        }
    }

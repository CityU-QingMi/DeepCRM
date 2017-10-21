    @Before
    public void prepare() {
        doInHibernate( this::sessionFactory, s -> {

            // it is important that the data associations remain as follows:
            //		* Johnny <-> Volkswagen Golf
            //		* Ricky <-> Subaru Impreza
            //		* Rosy -> none
            //		* none <- Renault Truck
            //
            // see #shouldHaveVehicle and #shouldHaveDriver

            Person person1 = new Person( "Johnny" );
            Person person2 = new Person( "Ricky" );
            Person person3 = new Person( "Rosy" );
            s.save( person1 );
            s.save( person2 );
            s.save( person3 );

            Vehicle vehicle1 = new Vehicle( "Volkswagen Golf" );
            vehicle1.setDriver( person1 );
            s.save( vehicle1 );

            Vehicle vehicle2 = new Vehicle( "Subaru Impreza" );
            vehicle2.setDriver( person2 );
            person2.setVehicle( vehicle2 );
            s.save( vehicle2 );

            Vehicle vehicle3 = new Vehicle( "Renault Truck" );
            s.save( vehicle3 );
        } );
    }

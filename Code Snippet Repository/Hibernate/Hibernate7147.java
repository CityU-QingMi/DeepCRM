    @Before
    public void prepare() {
        // Create garage, add 2 cars to garage
        doInJPA( this::sessionFactory, em -> {

            Garage garage = new Garage();
            Car car1 = new Car();
            Car car2 = new Car();
            garage.insert( car1 );
            garage.insert( car2 );

            em.persist( garage );
            em.persist( car1 );
            em.persist( car2 );

            garageId = garage.id;
            car1Id = car1.id;
            car2Id = car2.id;
        } );
    }

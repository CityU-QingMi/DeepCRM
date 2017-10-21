    @Test
    public void test() {
        // Remove garage
        doInJPA( this::sessionFactory, em -> {
            Garage toRemoveGarage = em.find( Garage.class, garageId );
            em.remove( toRemoveGarage );
        } );

        // Check if there is no garage but cars are still present
        doInJPA( this::sessionFactory, em -> {
            Garage foundGarage = em.find( Garage.class, garageId );
            Assert.assertNull( foundGarage );

            Car foundCar1 = em.find( Car.class, car1Id );
            Assert.assertEquals( car1Id, foundCar1.id );

            Car foundCar2 = em.find( Car.class, car2Id );
            Assert.assertEquals( car2Id, foundCar2.id );
        } );
    }

    @Test
    public void test() {
        Long addressId = doInJPA( this::entityManagerFactory, entityManager -> {
            //tag::spatial-types-point-creation-example[]
            Event event = new Event();
            event.setId( 1L);
            event.setName( "Hibernate ORM presentation");
            Point point = geometryFactory.createPoint( new Coordinate( 10, 5 ) );
            event.setLocation( point );

            entityManager.persist( event );
            //end::spatial-types-point-creation-example[]
            return event.getId();
        });

        doInJPA( this::entityManagerFactory, entityManager -> {
            Event event = entityManager.find( Event.class, addressId);
            Coordinate coordinate = event.getLocation().getCoordinate();
            assertEquals( 10.0d, coordinate.getOrdinate( Coordinate.X), 0.1);
            assertEquals( 5.0d, coordinate.getOrdinate( Coordinate.Y), 0.1);
        });

        doInJPA( this::entityManagerFactory, entityManager -> {
            Coordinate [] coordinates = new Coordinate[] {
                new Coordinate(1,1), new Coordinate(20,1), new Coordinate(20,20),
                new Coordinate(1,20), new Coordinate(1,1)
            };
            //tag::spatial-types-query-example[]
            Polygon window = geometryFactory.createPolygon( coordinates );
            Event event = entityManager.createQuery(
                "select e " +
                "from Event e " +
                "where within(e.location, :window) = true", Event.class)
            .setParameter("window", window)
            .getSingleResult();
            //end::spatial-types-query-example[]
            Coordinate coordinate = event.getLocation().getCoordinate();
            assertEquals( 10.0d, coordinate.getOrdinate( Coordinate.X), 0.1);
            assertEquals( 5.0d, coordinate.getOrdinate( Coordinate.Y), 0.1);
        });
    }

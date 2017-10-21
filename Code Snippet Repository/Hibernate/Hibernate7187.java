    @Test
    public void testMerge() {
        doInHibernate( this::sessionFactory, s -> {
            Person entity = s.find( Person.class, 1L );
            entity.name = "John";
            try {
                s.merge( entity );
            } catch ( RuntimeException e ) {
                fail( "Enhanced entity can't be merged: " + e.getMessage() );
            }
        } );
    }

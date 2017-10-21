    @Test
    public void testRefresh() {
        doInHibernate( this::sessionFactory, s -> {
            Person entity = s.find( Person.class, 1L );
            entity.name = "John";
            try {
                s.refresh( entity );
            } catch ( RuntimeException e ) {
                fail( "Enhanced entity can't be refreshed: " + e.getMessage() );
            }
        } );
    }

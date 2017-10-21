    @Test
    public void test() {
        EventListenerRegistry registry = sessionFactory().getServiceRegistry().getService( EventListenerRegistry.class );
        registry.prependListeners( EventType.FLUSH, new JpaFlushEventListener() );
        registry.prependListeners( EventType.LOAD, new ImmediateLoadTrap() );

        doInJPA( this::sessionFactory, em -> {

            em.find( Parent.class, parentID );

            // unwanted lazy load occurs on flush
        } );
    }

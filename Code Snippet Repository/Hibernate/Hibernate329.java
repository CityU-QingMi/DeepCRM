    @Test
    public void testLifecycle() {
        doInJPA( this::entityManagerFactory, entityManager -> {
            Event event = new Event( period );
            entityManager.persist( event );
        } );
        doInJPA( this::entityManagerFactory, entityManager -> {
            Event event = entityManager.createQuery( "from Event", Event.class ).getSingleResult();
            assertEquals( period, event.getSpan() );
        } );
        doInJPA( this::entityManagerFactory, entityManager -> {
            //tag::basic-jpa-convert-period-string-converter-immutability-plan-example[]
            Event event = entityManager.createQuery( "from Event", Event.class ).getSingleResult();
            event.setSpan(Period
                .ofYears( 3 )
                .plusMonths( 2 )
                .plusDays( 1 )
            );
            //end::basic-jpa-convert-period-string-converter-immutability-plan-example[]
        } );
    }

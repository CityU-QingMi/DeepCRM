    @Test
    @TestForIssue( jiraKey = "" )
    public void testIt() {
        AggregatedTypeValue _e1 = doInHibernate( this::sessionFactory, session -> {
            AggregatedTypeValue e1 = new AggregatedTypeValue();
            e1.id = 1L;
            TypeValue t1 = new TypeValue();
            t1.time = YearMonth.of(2017, 5);
            e1.oneValue = t1;
            TypeValue t2 = new TypeValue();
            t2.time = YearMonth.of(2016, 4);
            e1.otherValue = t2;
            session.save( e1 );

            return e1;
        } );

        doInHibernate( this::sessionFactory, session -> {
            AggregatedTypeValue e1 = session.get( AggregatedTypeValue.class, _e1.id );
            assertEquals(e1.oneValue.time, YearMonth.of(2017, 5));
            assertEquals(e1.otherValue.time, YearMonth.of(2016, 4));
            session.delete( e1 );
        } );
    }

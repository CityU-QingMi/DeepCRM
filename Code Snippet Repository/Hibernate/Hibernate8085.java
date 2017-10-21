    @Test
    public void testRightOuterEntityJoins() {
        doInHibernate( this::sessionFactory, session -> {
            // this should get all users even if they have no financial records
            List<Object[]> result = session.createQuery(
                    "select r.id, u.id, u.username " +
                            "from FinancialRecord r " +
                            "	right join User u on r.lastUpdateBy = u.username" +
                            "   order by u.id"
            ).list();

            assertThat( result.size(), is( 2 ) );

            Object[] steveAndAcme = result.get( 0 );
            assertThat( steveAndAcme[0], is( 1 ) );
            assertThat( steveAndAcme[2], is( "steve" ) );

            Object[] janeAndNull = result.get( 1 );
            assertNull( janeAndNull[0] );
            assertThat( janeAndNull[2], is( "jane" ) );
        } );
    }

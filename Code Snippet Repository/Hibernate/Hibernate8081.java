    @Test
    public void testLeftOuterEntityJoins() {
        doInHibernate( this::sessionFactory, session -> {
            // this should get all financial records even if their lastUpdateBy user is null
            List<Object[]> result = session.createQuery(
                    "select r.id, u.id, u.username " +
                            "from FinancialRecord r " +
                            "	left join User u on r.lastUpdateBy = u.username" +
                            "   order by r.id"
            ).list();
            assertThat( result.size(), is( 2 ) );

            Object[] stevesRecord = result.get( 0 );
            assertThat( stevesRecord[0], is( 1 ) );
            assertThat( stevesRecord[2], is( "steve" ) );

            Object[] noOnesRecord = result.get( 1 );
            assertThat( noOnesRecord[0], is( 2 ) );
            assertNull( noOnesRecord[2] );
        } );
    }

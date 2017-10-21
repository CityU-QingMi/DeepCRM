    @Test
    @TestForIssue(jiraKey = "")
    public void testJoinOnEntityJoinNode() {
        doInHibernate( this::sessionFactory, session -> {
            // this should get all financial records even if their lastUpdateBy user is null
            List<Object[]> result = session.createQuery(
                    "select u.username, c.name " +
                            "from FinancialRecord r " +
                            "	left join User u on r.lastUpdateBy = u.username " +
                            "   left join u.customer c " +
                            "   order by r.id"
            ).list();
            assertThat( result.size(), is( 2 ) );

            Object[] stevesRecord = result.get( 0 );
            assertThat( stevesRecord[0], is( "steve" ) );
            assertThat( stevesRecord[1], is( "Acme" ) );

            Object[] noOnesRecord = result.get( 1 );
            assertNull( noOnesRecord[0] );
            assertNull( noOnesRecord[1] );
        } );
    }

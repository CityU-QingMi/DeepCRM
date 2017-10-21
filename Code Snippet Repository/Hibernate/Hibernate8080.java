    @Test()
    public void testInnerEntityJoins() {
        doInHibernate( this::sessionFactory, session -> {

            // this should get financial records which have a lastUpdateBy user set
            List<Object[]> result = session.createQuery(
                    "select r.id, c.name, u.id, u.username " +
                            "from FinancialRecord r " +
                            "   inner join r.customer c " +
                            "	inner join User u on r.lastUpdateBy = u.username"
            ).list();

            assertThat( result.size(), is( 1 ) );
            Object[] steveAndAcme = result.get( 0 );
            assertThat( steveAndAcme[0], is( 1 ) );
            assertThat( steveAndAcme[1], is( "Acme" ) );
            assertThat( steveAndAcme[3], is( "steve" ) );

            // NOTE that this leads to not really valid SQL, although some databases might support it /
//			result = session.createQuery(
//					"select r.id, r.customer.name, u.id, u.username " +
//							"from FinancialRecord r " +
//							"	inner join User u on r.lastUpdateBy = u.username"
//			).list();
//			assertThat( result.size(), is( 1 ) );

        } );
    }

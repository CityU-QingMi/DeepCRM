    @Test
    @TestForIssue(jiraKey = "")
    public void testNoImpliedJoinGeneratedForEqualityComparison() {
        doInHibernate( this::sessionFactory, session -> {
            final HQLQueryPlan plan = sessionFactory().getQueryPlanCache().getHQLQueryPlan(
                    "select r.id, cust.name " +
                            "from FinancialRecord r " +
                            "	join Customer cust on r.customer = cust" +
                            "   order by r.id",
                    false,
                    Collections.EMPTY_MAP
            );
            assertEquals( 1, plan.getTranslators().length );
            final QueryTranslator translator = plan.getTranslators()[0];
            final String generatedSql = translator.getSQLString();

            int tableReferenceIndex = generatedSql.indexOf( " customer " );
            assertNotEquals("Generated SQL doesn't contain a table reference for customer", -1, tableReferenceIndex );
            int nextTableReferenceIndex = generatedSql.indexOf( " customer ", tableReferenceIndex + 1 );
            assertEquals("Generated SQL wrongly joined customer twice", -1, nextTableReferenceIndex );
        } );
    }

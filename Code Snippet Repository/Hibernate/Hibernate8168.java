    @Test
    @TestForIssue(jiraKey = "")
    public void testInheritanceReAliasing() {
        Session s = openSession();
        Transaction tx = s.beginTransaction();

        // Just assert that the query is successful
        List<Object[]> results = s.createQuery(
                "SELECT usedBy.id, usedBy.name, COUNT(inverse.id) " +
                "FROM " + AbstractConfigurationObject.class.getName() + " config " +
                "INNER JOIN config.usedBy usedBy " +
                "LEFT JOIN usedBy.uses inverse ON inverse.id = config.id " +
                "WHERE config.id = 0 " +
                "GROUP BY usedBy.id, usedBy.name",
                Object[].class
        ).getResultList();

        tx.commit();
        s.close();
    }

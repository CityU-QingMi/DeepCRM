    @Test
    public void testNestedOrderBySubqueryInFunction() {
        withLimit(s -> {
            Query q = s.createQuery(
                    "SELECT a.id FROM EntityA a " +
                    "ORDER BY CASE WHEN (" +
                        "SELECT 1 FROM EntityA s1 " +
                        "WHERE s1.id IN(" +
                            "LIMIT(" +
                                "(" +
                                    "SELECT 1 FROM EntityA sub " +
                                    "ORDER BY " +
                                    "CASE WHEN sub.name IS NULL THEN 1 ELSE 0 END, " +
                                    "sub.name DESC, " +
                                    "CASE WHEN sub.id IS NULL THEN 1 ELSE 0 END, " +
                                    "sub.id DESC" +
                                ")," +
                            "1)" +
                        ")" +
                    ") = 1 THEN 1 ELSE 0 END"
            );
            q.getResultList();
        });
    }

    @Test
    @TestForIssue(jiraKey = "")
    public void testGetElementsShouldNotThrowExceptionWhenResultContainsNullValue() {
        doInJPA(this::entityManagerFactory, entityManager -> {
            User user = entityManager.find(User.class, 1L);
            user.firstName = null;
        });
        doInJPA(this::entityManagerFactory, entityManager -> {
            List<Tuple> tuples = getTupleResult(entityManager);
            List<TupleElement<?>> result = tuples.get(0).getElements();
            assertEquals(2, result.size());
            assertEquals(BigInteger.class, result.get(0).getJavaType());
            assertEquals("id", result.get(0).getAlias());
            assertEquals(Object.class, result.get(1).getJavaType());
            assertEquals("firstname", result.get(1).getAlias());
        });
    }

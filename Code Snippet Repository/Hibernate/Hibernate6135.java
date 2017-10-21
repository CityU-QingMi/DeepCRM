    @Test
    public void testGetElementsShouldWorkProperly() {
        doInJPA(this::entityManagerFactory, entityManager -> {
            List<Tuple> tuples = getTupleResult(entityManager);
            List<TupleElement<?>> result = tuples.get(0).getElements();
            assertEquals(2, result.size());
            assertEquals(BigInteger.class, result.get(0).getJavaType());
            assertEquals("id", result.get(0).getAlias());
            assertEquals(String.class, result.get(1).getJavaType());
            assertEquals("firstname", result.get(1).getAlias());
        });
    }

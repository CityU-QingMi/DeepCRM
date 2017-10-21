    @Test
    public void testCollectionPositionalParametersAreAppendedToIfNonNull() {
        CollectionPositionalParams params = new CollectionPositionalParams();
        params.collection = new ArrayList<Integer>();
        params.collection.add(234);
        Collection<Integer> list = params.collection;
        new CommandLine(params).parse("3", "2", "1");
        assertSame(list, params.collection);
        assertEquals(Arrays.asList(234, 3, 2, 1), params.collection);
    }

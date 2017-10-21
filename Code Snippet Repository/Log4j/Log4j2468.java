    @Test
    public void testSortedSetPositionalParametersAreAppendedToIfNonNull() {
        SortedSetPositionalParams params = new SortedSetPositionalParams();
        params.sortedSet = new TreeSet<Integer>();
        params.sortedSet.add(234);
        SortedSet<Integer> list = params.sortedSet;
        new CommandLine(params).parse("3", "2", "1");
        assertSame(list, params.sortedSet);
        assertEquals(Arrays.asList(1, 2, 3, 234), new ArrayList<Integer>(params.sortedSet));
    }

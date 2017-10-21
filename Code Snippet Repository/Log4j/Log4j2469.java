    @Test
    public void testSetPositionalParametersAreAppendedToIfNonNull() {
        SetPositionalParams params = new SetPositionalParams();
        params.set = new TreeSet<Integer>();
        params.set.add(234);
        Set<Integer> list = params.set;
        new CommandLine(params).parse("3", "2", "1");
        assertSame(list, params.set);
        assertEquals(new HashSet(Arrays.asList(1, 2, 3, 234)), params.set);
    }

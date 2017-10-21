    @Parameterized.Parameters
    public static Collection<Object[]> data()
    {
        Object[][] data = new Object[][]{
            {new ArrayTrie<Integer>(128)},
            {new TreeTrie<Integer>()},
            {new ArrayTernaryTrie<Integer>(128)}
        };
        return Arrays.asList(data);
    }

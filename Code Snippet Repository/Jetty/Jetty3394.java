    @Parameterized.Parameters(name = "")
    public static Collection<Object[]> data()
    {
        Object[][] data = new Object[][]
            {
            {HttpVersion.HTTP_1_0.asString(), true}, 
            {HttpVersion.HTTP_1_1.asString(), true}, 
            {HttpVersion.HTTP_1_0.asString(), false}, 
            {HttpVersion.HTTP_1_1.asString(), false}
            };
        return Arrays.asList(data);
    }

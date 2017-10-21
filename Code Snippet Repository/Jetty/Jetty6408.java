    @Parameters
    public static Collection<Object[]> data()
    {
        // The various quoting of a String
        List<Object[]> data = new ArrayList<>();

        // @formatter:off
        data.add(new Object[] { "Hi", "\"Hi\"" });
        data.add(new Object[] { "Hello World", "\"Hello World\"" });
        data.add(new Object[] { "9.0.0", "\"9.0.0\"" });
        data.add(new Object[] { "Something \"Special\"", 
                                "\"Something \\\"Special\\\"\"" });
        data.add(new Object[] { "A Few\n\"Good\"\tMen", 
                                "\"A Few\\n\\\"Good\\\"\\tMen\"" });
        // @formatter:on

        return data;
    }

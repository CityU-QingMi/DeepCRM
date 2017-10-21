    @DataProvider(name = "")
    Object[][] paramValues() {
        return new Object[][] {
            {null, new String[0]},
            {"input", new String[] {"input"}},
            {Integer.valueOf(5), new String[] {"5"}},
            {new String[] {"foo"}, new String[] {"foo"}},
            {new Object[] {null}, new String[] {null}},
        };
    }

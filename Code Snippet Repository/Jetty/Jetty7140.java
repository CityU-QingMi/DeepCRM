    @Parameters(name = "")
    public static Collection<Object[]> data()
    {
        // The various Good UTF8 sequences as a String (hex form)
        List<Object[]> data = new ArrayList<>();

        // @formatter:off
        data.add(new Object[] { "7.7.1", 1000 });
        data.add(new Object[] { "7.7.2", 1001 });
        data.add(new Object[] { "7.7.3", 1002 });
        data.add(new Object[] { "7.7.4", 1003 });
        data.add(new Object[] { "7.7.5", 1007 });
        data.add(new Object[] { "7.7.6", 1008 });
        data.add(new Object[] { "7.7.7", 1009 });
        data.add(new Object[] { "7.7.8", 1010 });
        data.add(new Object[] { "7.7.9", 1011 });
        data.add(new Object[] { "IANA Assigned", 1012 });
        data.add(new Object[] { "IANA Assigned", 1013 });
        data.add(new Object[] { "IANA Assigned", 1014 });
        data.add(new Object[] { "7.7.10", 3000 });
        data.add(new Object[] { "7.7.11", 3999 });
        data.add(new Object[] { "7.7.12", 4000 });
        data.add(new Object[] { "7.7.13", 4999 });
        // @formatter:on

        return data;
    }

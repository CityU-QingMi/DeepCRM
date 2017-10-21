    @Parameters(name = "")
    public static Collection<Object[]> data()
    {
        // The various Good UTF8 sequences as a String (hex form)
        List<Object[]> data = new ArrayList<>();

        // @formatter:off
        data.add(new Object[] { "7.9.1", 0 });
        data.add(new Object[] { "7.9.2", 999 });
        data.add(new Object[] { "7.9.3", 1004 }); // RFC6455/UNDEFINED
        data.add(new Object[] { "7.9.4", 1005 }); // RFC6455/Cannot Be Transmitted
        data.add(new Object[] { "7.9.5", 1006 }); // RFC6455/Cannot Be Transmitted
        // data.add(new Object[] { "7.9.6", 1012 }); - IANA Defined
        // data.add(new Object[] { "7.9.7", 1013 }); - IANA Defined
        // data.add(new Object[] { "7.9.8", 1014 }); - IANA Defined
        data.add(new Object[] { "7.9.9", 1015 }); // RFC6455/Cannot Be Transmitted
        data.add(new Object[] { "7.9.10", 1016 });
        data.add(new Object[] { "7.9.11", 1100 });
        data.add(new Object[] { "7.9.12", 2000 });
        data.add(new Object[] { "7.9.13", 2999 });
        // -- close status codes, with undefined events in spec 
        data.add(new Object[] { "7.13.1", 5000 });
        data.add(new Object[] { "7.13.2", 65536 });
        // @formatter:on

        return data;
    }

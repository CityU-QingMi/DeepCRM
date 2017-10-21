    @Parameters(name="")
    public static List<String[]> testCases()
    {
        String data[][] = new String[][] { 
            {"","",null},
            {":80","","80"},
            {"host","host",null},
            {"host:80","host","80"},
            {"10.10.10.1","10.10.10.1",null},
            {"10.10.10.1:80","10.10.10.1","80"},
            {"[0::0::0::1]","[0::0::0::1]",null},
            {"[0::0::0::1]:80","[0::0::0::1]","80"},

            {null,null,null},
            {"host:",null,null},
            {"127.0.0.1:",null,null},
            {"[0::0::0::0::1]:",null,null},
            {"host:xxx",null,null},
            {"127.0.0.1:xxx",null,null},
            {"[0::0::0::0::1]:xxx",null,null},
            {"host:-80",null,null},
            {"127.0.0.1:-80",null,null},
            {"[0::0::0::0::1]:-80",null,null},
        };
        return Arrays.asList(data);
    }

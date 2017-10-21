    @Parameters(name="")
    public static List<String[]> testCases()
    {
        String data[][] = new String[][] { 
            // From old PathMapTest
            { "All matches",  "/animal/bird/path.tar.gz", "[/animal/bird/*=birds, /animal/*=animals, *.tar.gz=tarball, *.gz=gzipped, /=default]"},
            { "Dir matches",  "/animal/fish/", "[/animal/fish/*=fishes, /animal/*=animals, /=default]"},
            { "Dir matches",  "/animal/fish", "[/animal/fish/*=fishes, /animal/*=animals, /=default]"},
            { "Root matches", "/", "[=root, /=default]"},
            { "Dir matches",  "", "[/=default]"}
        };
        
        return Arrays.asList(data);
    }

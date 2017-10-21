    @Parameters(name="")
    public static List<String[]> testCases()
    {
        String data[][] = new String[][] { 
            // From old PathMapTest
            {"/abs/path","abspath"},
            {"/abs/path/xxx","default"},
            {"/abs/pith","default"},
            {"/abs/path/longer","longpath"},
            {"/abs/path/","default"},
            {"/abs/path/foo","default"},
            {"/animal/bird/eagle/bald","birds"},
            {"/animal/fish/shark/hammerhead","fishes"},
            {"/animal/insect/ladybug","animals"},
            {"/animal","animals"},
            {"/animal/","animals"},
            {"/animal/other","animals"},
            {"/animal/*","animals"},
            {"/downloads/distribution.tar.gz","tarball"},
            {"/downloads/script.gz","gzipped"},
            {"/animal/arhive.gz","animals"},
            {"/Other/path","default"},
            {"/\u20ACuro/path","money"},
            {"/","root"},
        
            // Extra tests
            {"/downloads/readme.txt","default"},
            {"/downloads/logs.tgz","default"},
            {"/main.css","default"}
        };
        
        return Arrays.asList(data);
    }

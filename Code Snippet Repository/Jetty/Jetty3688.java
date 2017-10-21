    @Parameterized.Parameters(name = "")
    public static List<Object[]> params()
    {
        List<Object[]> data = new ArrayList<>();

        String dirs[] = {"/testdir/", "/testdirlnk/", "/testdirprefixlnk/", "/testdirsuffixlnk/",
                "/testdirwraplnk/"};

        for (String dirname : dirs)
        {
            data.add(new Object[]{dirname, 200, "text/html", "Directory: " + dirname});
            data.add(new Object[]{dirname + "testfile.txt", 200, "text/plain", "Hello TestFile"});
            data.add(new Object[]{dirname + "testfilelnk.txt", 200, "text/plain", "Hello TestFile"});
            data.add(new Object[]{dirname + "testfileprefixlnk.txt", 200, "text/plain", "Hello TestFile"});
        }

        return data;
    }

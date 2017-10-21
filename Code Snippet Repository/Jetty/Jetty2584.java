    @Test
    public void testTrueMatch() throws IOException
    {
        String[][] matchCases = {
                // regex: *.jsp
                {"/.*.jsp", "/hello.jsp"},
                {"/.*.jsp", "/abc/hello.jsp"},

                // regex: /abc or /def
                {"/abc|/def", "/abc"},
                {"/abc|/def", "/def"},

                // regex: *.do or *.jsp
                {".*\\.do|.*\\.jsp", "/hello.do"},
                {".*\\.do|.*\\.jsp", "/hello.jsp"},
                {".*\\.do|.*\\.jsp", "/abc/hello.do"},
                {".*\\.do|.*\\.jsp", "/abc/hello.jsp"},

                {"/abc/.*.htm|/def/.*.htm", "/abc/hello.htm"},
                {"/abc/.*.htm|/def/.*.htm", "/abc/def/hello.htm"},

                // regex: /abc/*.jsp
                {"/abc/.*.jsp", "/abc/hello.jsp"},
                {"/abc/.*.jsp", "/abc/def/hello.jsp"}
        };

        for (String[] matchCase : matchCases)
        {
            assertMatch(true, matchCase);
        }
    }

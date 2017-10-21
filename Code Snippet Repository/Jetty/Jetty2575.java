    @Test
    public void testFalseMatch() throws IOException
    {
        String[][] matchCases = {

                {"/abc", "/abcd"},
                {"/abc/", "/abcd/"},

                {"/abc/path/longer", "/abc/path/longer/"},
                {"/abc/path/longer", "/abc/path/longer1"},
                {"/abc/path/longer/", "/abc/path/longer"},
                {"/abc/path/longer/", "/abc/path/longer1/"},

                {"/*.jsp", "/hello.jsp"},
                {"/abc/*.jsp", "/abc/hello.jsp"},

                {"*.jsp", "/hello.1jsp"},
                {"*.jsp", "/hello.jsp1"},
                {"*.jsp", "/hello.do"},

                {"*.jsp", "/abc/hello.do"},
                {"*.jsp", "/abc/def/hello.do"},
                {"*.jsp", "/abc.do"}
        };

        for (String[] matchCase : matchCases)
        {
            assertMatch(false, matchCase);
        }
    }

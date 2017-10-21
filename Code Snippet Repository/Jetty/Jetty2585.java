    @Test
    public void testFalseMatch() throws IOException
    {
        String[][] matchCases = {
                {"/abc/.*.jsp", "/hello.jsp"}
        };

        for (String[] matchCase : matchCases)
        {
            assertMatch(false, matchCase);
        }
    }

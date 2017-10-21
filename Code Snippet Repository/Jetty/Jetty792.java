    @Test
    public void testFindPath_Undeployed_Started()
    {
        List<String> expected = new ArrayList<String>();
        expected.add("undeployed");
        expected.add("deploying");
        expected.add("deployed");
        expected.add("starting");
        expected.add("started");
        assertPath("undeployed","started",expected);
    }

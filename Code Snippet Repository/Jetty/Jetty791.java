    @Test
    public void testFindPath_Started_Undeployed()
    {
        List<String> expected = new ArrayList<String>();
        expected.add("started");
        expected.add("stopping");
        expected.add("deployed");
        expected.add("undeploying");
        expected.add("undeployed");
        assertPath("started","undeployed",expected);
    }

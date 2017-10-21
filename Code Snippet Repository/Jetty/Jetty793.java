    @Test
    public void testFindPathMultiple() throws IOException
    {
        AppLifeCycle lifecycle = new AppLifeCycle();
        List<String> expected = new ArrayList<String>();

        File outputDir = testdir.getEmptyPathDir().toFile();

        // Modify graph to add new 'staging' -> 'staged' between 'deployed' and 'started'
        GraphOutputDot.write(lifecycle,new File(outputDir,"multiple-1.dot")); // before change
        lifecycle.insertNode(lifecycle.getPath("deployed","started").getEdge(0),"staging");
        GraphOutputDot.write(lifecycle,new File(outputDir,"multiple-2.dot")); // after first change
        lifecycle.insertNode(lifecycle.getPath("staging","started").getEdge(0),"staged");
        GraphOutputDot.write(lifecycle,new File(outputDir,"multiple-3.dot")); // after second change

        // Deployed -> Deployed
        expected.clear();
        assertPath(lifecycle,"deployed","deployed",expected);

        // Deployed -> Staged
        expected.clear();
        expected.add("deployed");
        expected.add("staging");
        expected.add("staged");
        assertPath(lifecycle,"deployed","staged",expected);

        // Staged -> Undeployed
        expected.clear();
        expected.add("staged");
        expected.add("starting");
        expected.add("started");
        expected.add("stopping");
        expected.add("deployed");
        expected.add("undeploying");
        expected.add("undeployed");
        assertPath(lifecycle,"staged","undeployed",expected);

        // Undeployed -> Started
        expected.clear();
        expected.add("undeployed");
        expected.add("deploying");
        expected.add("deployed");
        expected.add("staging");
        expected.add("staged");
        expected.add("starting");
        expected.add("started");
        assertPath(lifecycle,"undeployed","started",expected);
    }

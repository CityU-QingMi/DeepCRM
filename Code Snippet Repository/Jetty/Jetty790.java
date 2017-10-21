    private void assertPath(AppLifeCycle lifecycle, String from, String to, List<String> expected)
    {
        Node fromNode = lifecycle.getNodeByName(from);
        Node toNode = lifecycle.getNodeByName(to);
        Path actual = lifecycle.getPath(fromNode,toNode);
        String msg = "LifeCycle path from " + from + " to " + to;
        Assert.assertNotNull(msg + " should never be null",actual);

        if (expected.size() != actual.nodes())
        {
            System.out.println();
            System.out.printf("/* from '%s' -> '%s' */%n",from,to);
            System.out.println("/* Expected Path */");
            for (String path : expected)
            {
                System.out.println(path);
            }
            System.out.println("/* Actual Path */");
            for (Node path : actual.getNodes())
            {
                System.out.println(path.getName());
            }

            Assert.assertEquals(msg + " / count",expected.size(),actual.nodes());
        }

        for (int i = 0, n = expected.size(); i < n; i++)
        {
            Assert.assertEquals(msg + "[" + i + "]",expected.get(i),actual.getNode(i).getName());
        }
    }

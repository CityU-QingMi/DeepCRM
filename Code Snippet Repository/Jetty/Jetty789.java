    public void assertExpected(String msg, List<String> expectedOrder)
    {
        if (expectedOrder.size() != actualOrder.size())
        {
            System.out.println("/* Expected Path */");
            for (String path : expectedOrder)
            {
                System.out.println(path);
            }
            System.out.println("/* Actual Path */");
            for (Node path : actualOrder)
            {
                System.out.println(path.getName());
            }

            Assert.assertEquals(msg + " / count",expectedOrder.size(),actualOrder.size());
        }

        for (int i = 0, n = expectedOrder.size(); i < n; i++)
        {
            Assert.assertEquals(msg + "[" + i + "]",expectedOrder.get(i),actualOrder.get(i).getName());
        }
    }

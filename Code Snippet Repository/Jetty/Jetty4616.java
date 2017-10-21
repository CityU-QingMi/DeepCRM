    @SuppressWarnings("")
    public static void assertOrdered(String msg, List<String> expectedList, List<String> actualList)
    {
        try
        {
            Assert.assertEquals(msg, expectedList.size(), actualList.size());
            if (!expectedList.isEmpty())
                assertThat(msg, actualList, Matchers.contains(expectedList.toArray()));
        }
        catch (AssertionError e)
        {
            System.err.println("Expected: " + expectedList);
            System.err.println("Actual  : " + actualList);
            throw e;
        }
    }

    public static void assertContainsUnordered(String msg, Collection<String> expectedSet, Collection<String> actualSet)
    {
        try
        {
            Assert.assertEquals(msg, expectedSet.size(), actualSet.size());
            if (!expectedSet.isEmpty())
                assertThat(msg, actualSet, Matchers.containsInAnyOrder(expectedSet.toArray()));
        }
        catch (AssertionError e)
        {
            System.err.println("Expected: " + expectedSet.stream().sorted().collect(Collectors.toList()));
            System.err.println("Actual  : " + actualSet.stream().sorted().collect(Collectors.toList()));
            throw e;
        }
        
    }

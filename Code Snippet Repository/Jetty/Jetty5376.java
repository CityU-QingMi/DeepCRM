    public static void assertContainsUnordered(String msg, Collection<String> expectedSet, Collection<String> actualSet)
    {
        // same size?
        boolean mismatch = expectedSet.size() != actualSet.size();

        // test content
        Set<String> missing = new HashSet<>();
        for (String expected : expectedSet)
        {
            if (!actualSet.contains(expected))
            {
                missing.add(expected);
            }
        }

        if (mismatch || missing.size() > 0)
        {
            // build up detailed error message
            StringWriter message = new StringWriter();
            PrintWriter err = new PrintWriter(message);

            err.printf("%s: Assert Contains (Unordered)",msg);
            if (mismatch)
            {
                err.print(" [size mismatch]");
            }
            if (missing.size() >= 0)
            {
                err.printf(" [%d entries missing]",missing.size());
            }
            err.println();
            err.printf("Actual Entries (size: %d)%n",actualSet.size());
            for (String actual : actualSet)
            {
                char indicator = expectedSet.contains(actual)?' ':'>';
                err.printf("%s| %s%n",indicator,actual);
            }
            err.printf("Expected Entries (size: %d)%n",expectedSet.size());
            for (String expected : expectedSet)
            {
                char indicator = actualSet.contains(expected)?' ':'>';
                err.printf("%s| %s%n",indicator,expected);
            }
            err.flush();
            Assert.fail(message.toString());
        }
    }

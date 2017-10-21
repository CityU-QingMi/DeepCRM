    private void assertNoHttpClientPoolThreads(List<String> threadNames)
    {
        for (String threadName : threadNames)
        {
            if (threadName.startsWith("HttpClient@") && !threadName.endsWith("-scheduler"))
            {
                throw new AssertionError("Found non-scheduler HttpClient thread in <" +
                        threadNames.stream().collect(Collectors.joining("[", ", ", "]"))
                        + ">");
            }
        }
    }

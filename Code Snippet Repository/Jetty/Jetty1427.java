    @Parameters(name = "")
    public static Collection<Run[]> data()
    {
        Result[] results = {
                new Result(200, null, -1, null, false),
                new Result(200, null, -1, CONTENT, false),
                new Result(200, null, CONTENT.length(), null, true),
                new Result(200, null, CONTENT.length(), CONTENT, false),
                new Result(200, "text/html", -1, null, true),
                new Result(200, "text/html", -1, CONTENT, false),
                new Result(200, "text/html", CONTENT.length(), null, true),
                new Result(200, "text/html", CONTENT.length(), CONTENT, false)
        };

        List<Run[]> data = new ArrayList<>();

        // For each test result
        for (Result result : results)
        {
            // Loop over HTTP versions
            for (int v = 10; v <= 11; v++)
            {
                // Loop over chunks
                for (int chunks = 1; chunks <= 6; chunks++)
                {
                    // Loop over Connection values
                    for (ConnectionType connection : ConnectionType.values())
                    {
                        if (connection.isSupportedByHttp(v))
                        {
                            data.add(Run.as(result, v, chunks, connection));
                        }
                    }
                }
            }
        }
        return data;
    }

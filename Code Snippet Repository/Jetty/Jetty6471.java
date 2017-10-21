    @Parameters
    public static Collection<String[]> data()
    {
        List<String[]> data = new ArrayList<>();
        // @formatter:off
        // - not using right scheme
        data.add(new String[] { "http://localhost" });
        data.add(new String[] { "https://localhost" });
        data.add(new String[] { "file://localhost" });
        data.add(new String[] { "content://localhost" });
        data.add(new String[] { "jar://localhost" });
        // - non-absolute uri
        data.add(new String[] { "/mysocket" });
        data.add(new String[] { "/sockets/echo" });
        data.add(new String[] { "#echo" });
        data.add(new String[] { "localhost:8080/echo" });
        // @formatter:on
        return data;
    }

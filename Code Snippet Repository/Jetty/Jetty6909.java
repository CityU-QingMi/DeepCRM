    @Parameters
    public static Collection<Object[]> data()
    {
        List<Object[]> data = new ArrayList<>();
        // @formatter:off
        data.add(new Object[] { "ws://localhost/",      "http://localhost/" });
        data.add(new Object[] { "ws://localhost:8080/", "http://localhost:8080/" });
        data.add(new Object[] { "ws://webtide.com/",    "http://webtide.com/" });
        data.add(new Object[] { "ws://www.webtide.com/sockets/chat", "http://www.webtide.com/sockets/chat" });
        // @formatter:on
        return data;
    }

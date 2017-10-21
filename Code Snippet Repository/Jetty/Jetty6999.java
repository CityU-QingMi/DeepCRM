    @Parameters(name = "")
    public static List<Object[]> modes()
    {
        List<Object[]> modes = new ArrayList<>();

        for(TestCaseMessageSize size: TestCaseMessageSize.values())
        {
            modes.add(new Object[] { "Normal HTTP/WS", false, "ws", size, -1 });
            modes.add(new Object[] { "Encrypted HTTPS/WSS", true, "wss", size, -1 });
            int altInputBufSize = 15*1024;
            modes.add(new Object[] { "Normal HTTP/WS", false, "ws", size, altInputBufSize });
            modes.add(new Object[] { "Encrypted HTTPS/WSS", true, "wss", size, altInputBufSize });
        }

        return modes;
    }

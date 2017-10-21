    @Parameters
    public static Collection<WebSocketFrame[]> data()
    {
        List<WebSocketFrame[]> data = new ArrayList<>();
        // @formatter:off
        data.add(new WebSocketFrame[]
                { new PingFrame().setFin(false) });
        data.add(new WebSocketFrame[]
                { new PingFrame().setRsv1(true) });
        data.add(new WebSocketFrame[]
                { new PingFrame().setRsv2(true) });
        data.add(new WebSocketFrame[]
                { new PingFrame().setRsv3(true) });
        data.add(new WebSocketFrame[]
                { new PongFrame().setFin(false) });
        data.add(new WebSocketFrame[]
                { new PingFrame().setRsv1(true) });
        data.add(new WebSocketFrame[]
                { new PongFrame().setRsv2(true) });
        data.add(new WebSocketFrame[]
                { new PongFrame().setRsv3(true) });
        data.add(new WebSocketFrame[]
                { new CloseInfo().asFrame().setFin(false) });
        data.add(new WebSocketFrame[]
                { new CloseInfo().asFrame().setRsv1(true) });
        data.add(new WebSocketFrame[]
                { new CloseInfo().asFrame().setRsv2(true) });
        data.add(new WebSocketFrame[]
                { new CloseInfo().asFrame().setRsv3(true) });
        // @formatter:on
        return data;
    }

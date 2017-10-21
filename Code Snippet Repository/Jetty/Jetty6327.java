    @Test
    public void testOnTextPartial() throws Throwable
    {
        List<WebSocketFrame> frames = new ArrayList<>();
        frames.add(new TextFrame().setPayload("Saved").setFin(false));
        frames.add(new ContinuationFrame().setPayload(" by ").setFin(false));
        frames.add(new ContinuationFrame().setPayload("zero").setFin(true));

        PartialTrackingSocket socket = new PartialTrackingSocket();

        EventDriver driver = toEventDriver(socket);
        driver.onConnect();

        for (WebSocketFrame frame : frames)
        {
            driver.incomingFrame(frame);
        }

        Assert.assertThat("Captured Event Queue size",socket.eventQueue.size(),is(3));
        Assert.assertThat("Event[0]",socket.eventQueue.poll(),is("onPartial(\"Saved\",false)"));
        Assert.assertThat("Event[1]",socket.eventQueue.poll(),is("onPartial(\" by \",false)"));
        Assert.assertThat("Event[2]",socket.eventQueue.poll(),is("onPartial(\"zero\",true)"));
    }

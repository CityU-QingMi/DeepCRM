    @Test
    public void testManyIds() throws Exception
    {
        EventIdSocket ids = new EventIdSocket();
        EventIdServer idserver = new EventIdServer(server);
        new Thread(idserver).start();
        client.connectToServer(ids,server.getWsUri());
        idserver.awaitConnect();
        int from = 1000;
        int to = 2000;
        idserver.writeSequentialIds(from,to);
        idserver.close();
        int count = from - to;
        ids.messageQueue.awaitEventCount(count,4,TimeUnit.SECONDS);
        ids.awaitClose();
        // collect seen ids
        List<Integer> seen = new ArrayList<>();
        for(EventId id: ids.messageQueue)
        {
            // validate that ids don't repeat.
            Assert.assertFalse("Already saw ID: " + id.eventId, seen.contains(id.eventId));
            seen.add(id.eventId);
        }
        
        // validate that all expected ids have been seen (order is irrelevant here)
        for(int expected=from; expected<to; expected++)
        {
            Assert.assertTrue("Has expected id:"+expected,seen.contains(expected));
        }
    }

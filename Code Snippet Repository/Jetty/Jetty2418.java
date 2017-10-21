    @Test
    public void testRoundRobinBalancer() throws Exception
    {
        stickySessions = false;
        startBalancer(CounterServlet.class);
        for (int i = 0; i < 10; i++)
        {
            byte[] responseBytes = sendRequestToBalancer("/roundRobin");
            String returnedCounter = readFirstLine(responseBytes);
            // Counter should increment every other request
            String expectedCounter = String.valueOf(i / 2);
            Assert.assertEquals(expectedCounter, returnedCounter);
        }
    }

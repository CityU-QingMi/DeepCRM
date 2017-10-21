    @Test
    public void testStickySessionsBalancer() throws Exception
    {
        stickySessions = true;
        startBalancer(CounterServlet.class);
        for (int i = 0; i < 10; i++)
        {
            byte[] responseBytes = sendRequestToBalancer("/stickySessions");
            String returnedCounter = readFirstLine(responseBytes);
            // Counter should increment every request
            String expectedCounter = String.valueOf(i);
            Assert.assertEquals(expectedCounter, returnedCounter);
        }
    }

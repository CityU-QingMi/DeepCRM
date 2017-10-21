    @Test
    public void testRemotePortLoadIdCreation_ipv4() throws ServletException {
        final ServletRequest request = new RemoteAddressRequest("127.0.0.1", 12345);
        DoSFilter doSFilter = new DoSFilter();
        doSFilter.init(new NoOpFilterConfig());
        doSFilter.setRemotePort(true);

        try
        {
            RateTracker tracker = doSFilter.getRateTracker(request);
            assertThat("tracker.id", tracker.getId(), is("127.0.0.1:12345"));
        }
        finally
        {
            doSFilter.stopScheduler();
        }
    }

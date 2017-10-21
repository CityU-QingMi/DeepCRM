    @Test
    public void testRemotePortLoadIdCreation_ipv6() throws ServletException {
        final ServletRequest request = new RemoteAddressRequest("::192.9.5.5", 12345);
        DoSFilter doSFilter = new DoSFilter();
        doSFilter.init(new NoOpFilterConfig());
        doSFilter.setRemotePort(true);

        try
        {
            RateTracker tracker = doSFilter.getRateTracker(request);
            assertThat("tracker.id", tracker.getId(),
                    anyOf(
                            is("[::192.9.5.5]:12345"), // short form
                            is("[0:0:0:0:0:0:c009:505]:12345") // long form
                    ));
        }
        finally
        {
            doSFilter.stopScheduler();
        }
    }

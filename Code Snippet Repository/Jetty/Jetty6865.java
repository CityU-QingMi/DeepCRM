    @Test
    public void testConnectFailure()
    {
        IOState state = new IOState();
        StateTracker tracker = new StateTracker();
        state.addListener(tracker);
        assertState(state,ConnectionState.CONNECTING);

        // fail upgrade
        state.onFailedUpgrade();

        tracker.assertTransitions(ConnectionState.CLOSED);
        assertState(state,ConnectionState.CLOSED);

        assertInputAvailable(state,false);
        assertOutputAvailable(state,false);

        // not clean
        assertCleanClose(state,false);
        assertLocalInitiated(state,false);
        assertRemoteInitiated(state,false);
    }

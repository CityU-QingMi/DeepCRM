    @Test
    public void testConnectAbnormalClose()
    {
        IOState state = new IOState();
        StateTracker tracker = new StateTracker();
        state.addListener(tracker);
        assertState(state,ConnectionState.CONNECTING);

        // connect
        state.onConnected();
        assertInputAvailable(state,false);
        assertOutputAvailable(state,true);

        // open
        state.onOpened();
        assertInputAvailable(state,true);
        assertOutputAvailable(state,true);

        // disconnect
        state.onAbnormalClose(new CloseInfo(StatusCode.NO_CLOSE,"Oops"));

        assertInputAvailable(state,false);
        assertOutputAvailable(state,false);
        tracker.assertTransitions(ConnectionState.CONNECTED,ConnectionState.OPEN,ConnectionState.CLOSED);
        assertState(state,ConnectionState.CLOSED);

        // not clean
        assertCleanClose(state,false);
        assertLocalInitiated(state,false);
        assertRemoteInitiated(state,false);
    }

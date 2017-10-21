    @Test
    public void testConnectCloseRemoteInitiated()
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

        // close (remote initiated)
        state.onCloseRemote(new CloseInfo(StatusCode.NORMAL,"Hi"));
        assertInputAvailable(state,false);
        assertOutputAvailable(state,true);
        assertState(state,ConnectionState.CLOSING);

        // close (local response)
        state.onCloseLocal(new CloseInfo(StatusCode.NORMAL,"Hi"));

        assertInputAvailable(state,false);
        assertOutputAvailable(state,false);
        tracker.assertTransitions(ConnectionState.CONNECTED,ConnectionState.OPEN,ConnectionState.CLOSING,ConnectionState.CLOSED);
        assertState(state,ConnectionState.CLOSED);

        // not clean
        assertCleanClose(state,true);
        assertLocalInitiated(state,false);
        assertRemoteInitiated(state,true);
    }

    @Test
    public void testInit()
    {
        IOState state = new IOState();
        StateTracker tracker = new StateTracker();
        state.addListener(tracker);
        assertState(state,ConnectionState.CONNECTING);

        // do nothing

        tracker.assertTransitions();
        assertState(state,ConnectionState.CONNECTING);

        // not connected yet
        assertInputAvailable(state,false);
        assertOutputAvailable(state,false);

        // no close yet
        assertCleanClose(state,false);
        assertLocalInitiated(state,false);
        assertRemoteInitiated(state,false);
    }

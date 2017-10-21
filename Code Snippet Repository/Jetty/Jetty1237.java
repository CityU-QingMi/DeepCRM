    @Override
    public void onPreface()
    {
        // SPEC: send a SETTINGS frame upon receiving the preface.
        Map<Integer, Integer> settings = notifyPreface(this);
        if (settings == null)
            settings = Collections.emptyMap();
        SettingsFrame settingsFrame = new SettingsFrame(settings, false);

        WindowUpdateFrame windowFrame = null;
        int sessionWindow = getInitialSessionRecvWindow() - FlowControlStrategy.DEFAULT_WINDOW_SIZE;
        if (sessionWindow > 0)
        {
            updateRecvWindow(sessionWindow);
            windowFrame = new WindowUpdateFrame(0, sessionWindow);
        }

        if (windowFrame == null)
            frames(null, Callback.NOOP, settingsFrame, Frame.EMPTY_ARRAY);
        else
            frames(null, Callback.NOOP, settingsFrame, windowFrame);
    }

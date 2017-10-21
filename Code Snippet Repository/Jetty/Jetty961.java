        @Override
        public void onOpen()
        {
            Map<Integer, Integer> settings = listener.onPreface(getSession());
            if (settings == null)
                settings = Collections.emptyMap();

            PrefaceFrame prefaceFrame = new PrefaceFrame();
            SettingsFrame settingsFrame = new SettingsFrame(settings, false);

            ISession session = getSession();

            int windowDelta = client.getInitialSessionRecvWindow() - FlowControlStrategy.DEFAULT_WINDOW_SIZE;
            if (windowDelta > 0)
            {
                session.updateRecvWindow(windowDelta);
                session.frames(null, this, prefaceFrame, settingsFrame, new WindowUpdateFrame(0, windowDelta));
            }
            else
            {
                session.frames(null, this, prefaceFrame, settingsFrame);
            }
            // Only start reading from server after we have sent the client preface,
            // otherwise we risk to read the server preface (a SETTINGS frame) and
            // reply to that before we have the chance to send the client preface.
            super.onOpen();
        }

    @Override
    public void windowUpdate(ISession session, IStream stream, WindowUpdateFrame frame)
    {
        super.windowUpdate(session, stream, frame);

        // Window updates cannot be negative.
        // The SettingsFrame.INITIAL_WINDOW_SIZE setting
        // only influences the *stream* window size.
        // Therefore the session window can only be enlarged,
        // and here we keep track of its max value.

        // Updating the max session recv window is done here
        // so that if a peer decides to send an unilateral
        // window update to enlarge the session window,
        // without the corresponding data consumption, here
        // we can track it.
        // Note that it is not perfect, since there is a time
        // window between the session recv window being updated
        // before the window update frame is sent, and the
        // invocation of this method: in between data may arrive
        // and reduce the session recv window size.
        // But eventually the max value will be seen.

        // Note that we cannot avoid the time window described
        // above by updating the session recv window from here
        // because there is a race between the sender and the
        // receiver: the sender may receive a window update and
        // send more data, while this method has not yet been
        // invoked; when the data is received the session recv
        // window may become negative and the connection will
        // be closed (per specification).

        if (frame.getStreamId() == 0)
        {
            int sessionWindow = session.updateRecvWindow(0);
            Atomics.updateMax(maxSessionRecvWindow, sessionWindow);
        }
    }

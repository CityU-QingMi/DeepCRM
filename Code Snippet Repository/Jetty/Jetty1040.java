    @Override
    public void frames(IStream stream, Callback callback, Frame frame, Frame... frames)
    {
        // We want to generate as late as possible to allow re-prioritization;
        // generation will happen while processing the entries.

        // The callback needs to be notified only when the last frame completes.

        int length = frames.length;
        if (length == 0)
        {
            frame(new ControlEntry(frame, stream, callback), true);
        }
        else
        {
            callback = new CountingCallback(callback, 1 + length);
            frame(new ControlEntry(frame, stream, callback), false);
            for (int i = 1; i <= length; ++i)
                frame(new ControlEntry(frames[i - 1], stream, callback), i == length);
        }
    }

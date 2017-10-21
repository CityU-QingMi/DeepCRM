    @Override
    public int priority(PriorityFrame frame, Callback callback)
    {
        int streamId = frame.getStreamId();
        IStream stream = streams.get(streamId);
        if (stream == null)
        {
            streamId = streamIds.getAndAdd(2);
            frame = new PriorityFrame(streamId, frame.getParentStreamId(),
                    frame.getWeight(), frame.isExclusive());
        }
        control(stream, callback, frame);
        return streamId;
    }

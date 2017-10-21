    @Override
    public void newStream(HeadersFrame frame, Promise<Stream> promise, Stream.Listener listener)
    {
        // Synchronization is necessary to atomically create
        // the stream id and enqueue the frame to be sent.
        boolean queued;
        synchronized (this)
        {
            int streamId = frame.getStreamId();
            if (streamId <= 0)
            {
                streamId = streamIds.getAndAdd(2);
                PriorityFrame priority = frame.getPriority();
                priority = priority == null ? null : new PriorityFrame(streamId, priority.getParentStreamId(),
                        priority.getWeight(), priority.isExclusive());
                frame = new HeadersFrame(streamId, frame.getMetaData(), priority, frame.isEndStream());
            }
            final IStream stream = createLocalStream(streamId, promise);
            if (stream == null)
                return;
            stream.setListener(listener);

            ControlEntry entry = new ControlEntry(frame, stream, new PromiseCallback<>(promise, stream));
            queued = flusher.append(entry);
        }
        // Iterate outside the synchronized block.
        if (queued)
            flusher.iterate();
    }

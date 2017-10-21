    @Override
    public void push(IStream stream, Promise<Stream> promise, PushPromiseFrame frame, Stream.Listener listener)
    {
        // Synchronization is necessary to atomically create
        // the stream id and enqueue the frame to be sent.
        boolean queued;
        synchronized (this)
        {
            int streamId = streamIds.getAndAdd(2);
            frame = new PushPromiseFrame(frame.getStreamId(), streamId, frame.getMetaData());

            final IStream pushStream = createLocalStream(streamId, promise);
            if (pushStream == null)
                return;
            pushStream.setListener(listener);

            ControlEntry entry = new ControlEntry(frame, pushStream, new PromiseCallback<>(promise, pushStream));
            queued = flusher.append(entry);
        }
        // Iterate outside the synchronized block.
        if (queued)
            flusher.iterate();
    }

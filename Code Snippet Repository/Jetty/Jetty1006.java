        private void offer(Stream stream, Frame frame, Callback callback)
        {
            if (LOGGER.isDebugEnabled())
                LOGGER.debug("SPC queueing {} for {} on {}", frame, stream, stream.getSession());
            synchronized (lock)
            {
                Deque<FrameInfo> deque = frames.computeIfAbsent(stream, s -> new ArrayDeque<>());
                deque.offer(new FrameInfo(frame, callback));
            }
            iterate();
        }

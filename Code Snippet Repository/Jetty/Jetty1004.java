        private void offer(Stream stream, Frame frame, Callback callback)
        {
            if (LOGGER.isDebugEnabled())
                LOGGER.debug("CPS queueing {} for {} on {}", frame, stream, stream.getSession());
            boolean connected;
            synchronized (lock)
            {
                Deque<FrameInfo> deque = frames.computeIfAbsent(stream, s -> new ArrayDeque<>());
                deque.offer(new FrameInfo(frame, callback));
                connected = proxyToServerSession != null;
            }
            if (connected)
                iterate();
            else
                connect();
        }

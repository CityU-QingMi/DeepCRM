    private void unlockMsg(MsgType type)
    {
        while (true)
        {
            int state = msgState.get();

            switch (type)
            {
                case BLOCKING:
                    if ((state & BLOCK_MASK) == 0)
                        throw new IllegalStateException(String.format("Not Blocking in state %x", state));
                    if (msgState.compareAndSet(state, state & ~BLOCK_MASK))
                        return;
                    break;

                case ASYNC:
                    if ((state & ASYNC_MASK) == 0)
                        throw new IllegalStateException(String.format("Not Async in %x", state));
                    if (msgState.compareAndSet(state, state - 1))
                        return;
                    break;

                case STREAMING:
                    if ((state & STREAM_MASK) == 0)
                        throw new IllegalStateException(String.format("Not Streaming in state %x", state));
                    if (msgState.compareAndSet(state, state & ~STREAM_MASK))
                        return;
                    break;

                case PARTIAL_BINARY:
                    if (msgState.compareAndSet(PARTIAL_BINARY_MASK, 0))
                        return;
                    throw new IllegalStateException(String.format("Not Partial Binary in state %x", state));

                case PARTIAL_TEXT:
                    if (msgState.compareAndSet(PARTIAL_TEXT_MASK, 0))
                        return;
                    throw new IllegalStateException(String.format("Not Partial Text in state %x", state));

            }
        }
    }

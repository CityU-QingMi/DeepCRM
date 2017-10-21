    private void terminate(Throwable cause)
    {
        while (true)
        {
            CloseState current = closed.get();
            switch (current)
            {
                case NOT_CLOSED:
                case LOCALLY_CLOSED:
                case REMOTELY_CLOSED:
                {
                    if (closed.compareAndSet(current, CloseState.CLOSED))
                    {
                        flusher.terminate(cause);
                        for (IStream stream : streams.values())
                            stream.close();
                        streams.clear();
                        disconnect();
                        return;
                    }
                    break;
                }
                default:
                {
                    return;
                }
            }
        }
    }

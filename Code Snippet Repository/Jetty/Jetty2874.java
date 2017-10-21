    @Override
    public void flush() throws IOException
    {
        while (true)
        {
            switch (_state.get())
            {
                case OPEN:
                    write(BufferUtil.hasContent(_aggregate) ? _aggregate : BufferUtil.EMPTY_BUFFER, false);
                    return;

                case ASYNC:
                    throw new IllegalStateException("isReady() not called");

                case READY:
                    if (!_state.compareAndSet(OutputState.READY, OutputState.PENDING))
                        continue;
                    new AsyncFlush().iterate();
                    return;

                case PENDING:
                    return;
                    
                case UNREADY:
                    throw new WritePendingException();

                case ERROR:
                    throw new EofException(_onError);

                case CLOSED:
                    return;

                default:
                    throw new IllegalStateException();
            }
        }
    }

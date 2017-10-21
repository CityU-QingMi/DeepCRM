    protected final void close(Throwable failure)
    {
        while(true)
        {
            State s = _state.get();
            switch(s)
            {
                case OPEN:
                case ISHUT: // Already ishut
                case OSHUT: // Already oshut
                    if (!_state.compareAndSet(s,State.CLOSED))
                        continue;
                    doOnClose(failure);
                    return;

                case ISHUTTING: // Somebody else ishutting
                case OSHUTTING: // Somebody else oshutting
                    if (!_state.compareAndSet(s,State.CLOSED))
                        continue;
                    // The thread doing the IO SHUT will call doOnClose
                    return;

                case CLOSED: // already closed
                    return;
            }
        }
    }

    protected final void shutdownInput()
    {
        while(true)
        {
            State s = _state.get();
            switch(s)
            {
                case OPEN:
                    if (!_state.compareAndSet(s,State.ISHUTTING))
                        continue;
                    try
                    {
                        doShutdownInput();
                    }
                    finally
                    {
                        if(!_state.compareAndSet(State.ISHUTTING,State.ISHUT))
                        {
                            // If somebody else switched to CLOSED while we were ishutting,
                            // then we do the close for them
                            if (_state.get()==State.CLOSED)
                                doOnClose(null);
                            else
                                throw new IllegalStateException();
                        }
                    }
                    return;

                case ISHUTTING:  // Somebody else ishutting
                case ISHUT: // Already ishut
                    return;

                case OSHUTTING:
                    if (!_state.compareAndSet(s,State.CLOSED))
                        continue;
                    // The thread doing the OSHUT will close
                    return;

                case OSHUT:
                    if (!_state.compareAndSet(s,State.CLOSED))
                        continue;
                    // Already OSHUT so we close
                    doOnClose(null);
                    return;

                case CLOSED: // already closed
                    return;
            }
        }
    }

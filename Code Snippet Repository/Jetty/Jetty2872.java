    void closed()
    {
        while (true)
        {
            OutputState state = _state.get();
            switch (state)
            {
                case CLOSED:
                {
                    return;
                }
                case UNREADY:
                {
                    if (_state.compareAndSet(state, OutputState.ERROR))
                        _writeListener.onError(_onError == null ? new EofException("Async closed") : _onError);
                    break;
                }
                default:
                {
                    if (!_state.compareAndSet(state, OutputState.CLOSED))
                        break;

                    try
                    {
                        _channel.getResponse().closeOutput();
                    }
                    catch (Throwable x)
                    {
                        if (LOG.isDebugEnabled())
                            LOG.debug(x);
                        abort(x);
                    }
                    finally
                    {
                        releaseBuffer();
                    }
                    // Return even if an exception is thrown by closeOutput().
                    return;
                }
            }
        }
    }

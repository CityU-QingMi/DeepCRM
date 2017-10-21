        @Override
        protected void onCompleteSuccess()
        {
            while (true)
            {
                OutputState last = _state.get();
                switch (last)
                {
                    case PENDING:
                        if (!_state.compareAndSet(OutputState.PENDING, OutputState.ASYNC))
                            continue;
                        break;

                    case UNREADY:
                        if (!_state.compareAndSet(OutputState.UNREADY, OutputState.READY))
                            continue;
                        if (_last)
                            closed();
                        if (_channel.getState().onWritePossible())
                            _channel.execute(_channel);
                        break;

                    case CLOSED:
                        break;

                    default:
                        throw new IllegalStateException();
                }
                break;
            }
        }

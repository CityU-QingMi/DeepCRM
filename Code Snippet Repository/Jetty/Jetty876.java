        @Override
        public void run()
        {
            while (true)
            {
                State current = state.get();
                if (LOG.isDebugEnabled())
                    LOG.debug("Running, state={}", current);
                switch (current)
                {
                    case DISPATCH:
                    {
                        if (state.compareAndSet(current, State.EXECUTE))
                            runnable.run();
                        continue;
                    }
                    case EXECUTE:
                    {
                        if (state.compareAndSet(current, State.IDLE))
                            return;
                        continue;
                    }
                    case SCHEDULE:
                    {
                        if (state.compareAndSet(current, State.DISPATCH))
                            continue;
                        throw new IllegalStateException();
                    }
                    default:
                    {
                        throw new IllegalStateException();
                    }
                }
            }
        }

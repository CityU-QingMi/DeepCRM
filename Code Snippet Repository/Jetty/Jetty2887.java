        public void waitUntilClosedOrIdleFor(long idleFor,TimeUnit units)
        {
            Thread.yield();
            int size=getOutput().remaining();
            while (isOpen())
            {
                try
                {
                    if (!_closed.await(idleFor,units))
                    {
                        if (size==getOutput().remaining())
                        {
                            if (LOG.isDebugEnabled())
                                LOG.debug("idle for {} {}",idleFor,units);
                            return;
                        }
                        size=getOutput().remaining();
                    }
                }
                catch(Exception e)
                {
                    LOG.warn(e);
                }
            }
        }

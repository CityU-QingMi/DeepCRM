        @Override
        public void run()
        {
            if (done.compareAndSet(false, true))
            {
                boolean closed = isClosed();
                lock();
                try
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("Validated {}", connection);
                    quarantine.remove(connection);
                    if (!closed)
                        deactivate(connection);
                }
                finally
                {
                    unlock();
                }

                idle(connection, closed);
                proceed();
            }
        }

        private Runnable reservedWait()
        {
            if (LOG.isDebugEnabled())
                LOG.debug("{} waiting", this);

            Runnable task = null;
            while (isRunning() && task==null)
            {
                boolean idle = false;

                try (Locker.Lock lock = _locker.lock())
                {
                    if (_task == null)
                    {
                        try
                        {
                            if (_idleTime == 0)
                                _wakeup.await();
                            else
                                idle = !_wakeup.await(_idleTime, _idleTimeUnit);
                        }
                        catch (InterruptedException e)
                        {
                            LOG.ignore(e);
                        }
                    }
                    task = _task;
                    _task = null;
                }

                if (idle)
                {
                    // Because threads are held in a stack, excess threads will be
                    // idle.  However, we cannot remove threads from the bottom of
                    // the stack, so we submit a poison pill job to stop the thread
                    // on top of the stack (which unfortunately will be the most
                    // recently used)
                    if (LOG.isDebugEnabled())
                        LOG.debug("{} IDLE", this);
                    tryExecute(STOP);
                }
            }

            if (LOG.isDebugEnabled())
                LOG.debug("{} task={}", this, task);

            return task==null?STOP:task;
        }

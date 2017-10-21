        @Override
        public void run()
        {
            while (isRunning())
            {
                try
                {
                    String log = _queue.poll(10,TimeUnit.SECONDS);
                    if (log!=null)
                        AsyncNCSARequestLog.super.write(log);

                    while(!_queue.isEmpty())
                    {
                        log=_queue.poll();
                        if (log!=null)
                            AsyncNCSARequestLog.super.write(log);
                    }
                }
                catch (IOException e)
                {
                    LOG.warn(e);
                }
                catch (InterruptedException e)
                {
                    LOG.ignore(e);
                }
            }
        }

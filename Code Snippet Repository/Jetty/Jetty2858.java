    protected void blockForContent() throws IOException
    {
        try
        {
            _waitingForContent = true;
            _channelState.getHttpChannel().onBlockWaitForContent();

            boolean loop = false;
            long timeout = 0;
            while (true)
            {
                if (_blockUntil != 0)
                {
                    timeout = TimeUnit.NANOSECONDS.toMillis(_blockUntil - System.nanoTime());
                    if (timeout <= 0)
                        throw new TimeoutException(String.format("Blocking timeout %d ms", getBlockingTimeout()));
                }

                // This method is called from a loop, so we just
                // need to check the timeout before and after waiting.
                if (loop)
                    break;

                if (LOG.isDebugEnabled())
                    LOG.debug("{} blocking for content timeout={}", this, timeout);
                if (timeout > 0)
                    _inputQ.wait(timeout);
                else
                    _inputQ.wait();

                loop = true;
            }
        }
        catch (Throwable x)
        {
            _channelState.getHttpChannel().onBlockWaitForContentFailure(x);
        }
    }

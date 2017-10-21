            @Override
            public void failed(final Throwable x)
            {
                // This means that a write of data has failed.  Writes are done
                // only if there is an active writeflusher or a read needed to write
                // data.  In either case the appropriate callback is passed on.
                boolean fail_filler;
                synchronized (DecryptedEndPoint.this)
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("write failed {}", SslConnection.this, x);

                    BufferUtil.clear(_encryptedOutput);
                    releaseEncryptedOutputBuffer();

                    _cannotAcceptMoreAppDataToFlush = false;
                    fail_filler = _fillRequiresFlushToProgress;
                    if (_fillRequiresFlushToProgress)
                        _fillRequiresFlushToProgress = false;
                }

                failedCallback(new Callback()
                {
                    @Override
                    public void failed(Throwable x)
                    {
                        if (fail_filler)
                            getFillInterest().onFail(x);
                        getWriteFlusher().onFail(x);
                    }
                }, x);
            }

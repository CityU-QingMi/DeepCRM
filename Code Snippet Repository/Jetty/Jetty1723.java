            @Override
            public void succeeded()
            {
                // This means that a write of encrypted data has completed.  Writes are done
                // only if there is a pending writeflusher or a read needed to write
                // data.  In either case the appropriate callback is passed on.
                boolean fillable = false;
                synchronized (DecryptedEndPoint.this)
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("write.complete {}", SslConnection.this.getEndPoint());

                    releaseEncryptedOutputBuffer();

                    _cannotAcceptMoreAppDataToFlush = false;

                    if (_fillRequiresFlushToProgress)
                    {
                        _fillRequiresFlushToProgress = false;
                        fillable = true;
                    }
                }
                if (fillable)
                    getFillInterest().fillable();
                _runCompleteWrite.run();
            }

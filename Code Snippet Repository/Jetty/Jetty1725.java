        @Override
        protected void needsFillInterest() throws IOException
        {
            // This means that the decrypted data consumer has called the fillInterested
            // method on the DecryptedEndPoint, so we have to work out if there is
            // decrypted data to be filled or what callbacks to setup to be told when there
            // might be more encrypted data available to attempt another call to fill
            boolean fillable;
            boolean write = false;
            synchronized (DecryptedEndPoint.this)
            {
                // Do we already have some app data, then app can fill now so return true
                fillable = (BufferUtil.hasContent(_decryptedInput))
                        // or if we have encryptedInput and have not underflowed yet, the it is worth trying a fill
                        || BufferUtil.hasContent(_encryptedInput) && !_underFlown;

                // If we have no encrypted data to decrypt OR we have some, but it is not enough
                if (!fillable)
                {
                    // We are not ready to read data

                    // Are we actually write blocked?
                    if (_fillRequiresFlushToProgress)
                    {
                        // we must be blocked trying to write before we can read

                        // Do we have data to write
                        if (BufferUtil.hasContent(_encryptedOutput))
                        {
                            // write it
                            _cannotAcceptMoreAppDataToFlush = true;
                            write = true;
                        }
                        else
                        {
                            // we have already written the net data
                            // pretend we are readable so the wrap is done by next readable callback
                            _fillRequiresFlushToProgress = false;
                            fillable=true;
                        }
                    }
                }
            }
            if (write)
                getEndPoint().write(_writeCallback, _encryptedOutput);
            else if (fillable)
                getExecutor().execute(_runFillable);
            else
                ensureFillInterested();
        }

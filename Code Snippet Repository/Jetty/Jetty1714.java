    @Override
    public void onFillable()
    {
        // onFillable means that there are encrypted bytes ready to be filled.
        // however we do not fill them here on this callback, but instead wakeup
        // the decrypted readInterest and/or writeFlusher so that they will attempt
        // to do the fill and/or flush again and these calls will do the actually
        // filling.

        if (LOG.isDebugEnabled())
            LOG.debug("onFillable enter {}", _decryptedEndPoint);

        // We have received a close handshake, close the end point to send FIN.
        if (_decryptedEndPoint.isInputShutdown())
            _decryptedEndPoint.close();

        // wake up whoever is doing the fill or the flush so they can
        // do all the filling, unwrapping, wrapping and flushing
        _decryptedEndPoint.getFillInterest().fillable();

        // If we are handshaking, then wake up any waiting write as well as it may have been blocked on the read
        boolean runComplete = false;
        synchronized(_decryptedEndPoint)
        {
            if (_decryptedEndPoint._flushRequiresFillToProgress)
            {
                _decryptedEndPoint._flushRequiresFillToProgress = false;
                runComplete = true;
            }
        }
        if (runComplete)
            _runCompleteWrite.run();

        if (LOG.isDebugEnabled())
            LOG.debug("onFillable exit {}", _decryptedEndPoint);
    }

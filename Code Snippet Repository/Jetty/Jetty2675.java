    private int checkNonce(Digest digest, Request request)
    {
        // firstly let's expire old nonces
        long expired = request.getTimeStamp() - getMaxNonceAge();
        Nonce nonce = _nonceQueue.peek();
        while (nonce != null && nonce._ts < expired)
        {
            _nonceQueue.remove(nonce);
            _nonceMap.remove(nonce._nonce);
            nonce = _nonceQueue.peek();
        }

        // Now check the requested nonce
        try
        {
            nonce = _nonceMap.get(digest.nonce);
            if (nonce == null)
                return 0;

            long count = Long.parseLong(digest.nc, 16);
            if (count >= _maxNC)
                return 0;

            if (nonce.seen((int)count))
                return -1;

            return 1;
        }
        catch (Exception e)
        {
            LOG.ignore(e);
        }
        return -1;
    }

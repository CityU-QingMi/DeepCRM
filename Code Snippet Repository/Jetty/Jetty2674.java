    public String newNonce(Request request)
    {
        Nonce nonce;

        do
        {
            byte[] nounce = new byte[24];
            _random.nextBytes(nounce);

            nonce = new Nonce(new String(B64Code.encode(nounce)), request.getTimeStamp(), getMaxNonceCount());
        }
        while (_nonceMap.putIfAbsent(nonce._nonce, nonce) != null);
        _nonceQueue.add(nonce);

        return nonce._nonce;
    }

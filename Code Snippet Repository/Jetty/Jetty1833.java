    public String newNonce(long ts)
    {
        // long ts=request.getTimeStamp();
        long sk = nonceSecret;

        byte[] nounce = new byte[24];
        for (int i = 0; i < 8; i++)
        {
            nounce[i] = (byte) (ts & 0xff);
            ts = ts >> 8;
            nounce[8 + i] = (byte) (sk & 0xff);
            sk = sk >> 8;
        }

        byte[] hash = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(nounce, 0, 16);
            hash = md.digest();
        }
        catch (Exception e)
        {
            LOG.warn(e);
        }

        for (int i = 0; i < hash.length; i++)
        {
            nounce[8 + i] = hash[i];
            if (i == 23) break;
        }

        return new String(B64Code.encode(nounce));
    }

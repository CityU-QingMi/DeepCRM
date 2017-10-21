    @Override
    public void onWebSocketBinary(byte[] payload, int offset, int len)
    {
        try
        {
            messages.add("binary[sha1="+Sha1Sum.calculate(payload,offset,len)+"]");
        }
        catch (NoSuchAlgorithmException e)
        {
            messages.add("ERROR: Unable to caclulate Binary SHA1: " + e.getMessage());
            e.printStackTrace();
        }
    }

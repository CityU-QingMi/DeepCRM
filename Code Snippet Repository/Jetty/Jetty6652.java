    public void reset(byte[] mask)
    {
        this.maskBytes = mask;
        int maskInt = 0;
        if (mask != null)
        {
            for (byte maskByte : mask)
                maskInt = (maskInt << 8) + (maskByte & 0xFF);
        }
        this.maskInt = maskInt;
        this.maskOffset = 0;
    }

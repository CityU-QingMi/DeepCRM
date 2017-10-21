    @Override
    public void encode(Long object, OutputStream os) throws EncodeException, IOException
    {
        byte b[] = new byte[8];
        long v = object;
        b[0] = (byte)(v >>> 56);
        b[1] = (byte)(v >>> 48);
        b[2] = (byte)(v >>> 40);
        b[3] = (byte)(v >>> 32);
        b[4] = (byte)(v >>> 24);
        b[5] = (byte)(v >>> 16);
        b[6] = (byte)(v >>> 8);
        b[7] = (byte)(v >>> 0);
        os.write(b,0,8);
    }

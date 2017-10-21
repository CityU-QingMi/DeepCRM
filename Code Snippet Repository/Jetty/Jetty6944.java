    public static byte[] mask(final byte[] data, final byte mask[])
    {
        Assert.assertThat("Mask.length",mask.length,is(4));
        int len = data.length;
        byte ret[] = new byte[len];
        System.arraycopy(data,0,ret,0,len);
        for (int i = 0; i < len; i++)
        {
            ret[i] ^= mask[i % 4];
        }
        return ret;
    }

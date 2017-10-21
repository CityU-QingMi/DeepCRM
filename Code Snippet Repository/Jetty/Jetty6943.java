    public static void putLengthAndMask(ByteBuffer buf, int length, byte mask[])
    {
        if (mask != null)
        {
            Assert.assertThat("Mask.length",mask.length,is(4));
            putLength(buf,length,(mask != null));
            buf.put(mask);
        }
        else
        {
            putLength(buf,length,false);
        }
    }

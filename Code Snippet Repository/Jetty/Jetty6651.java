    @Override
    public void process(ByteBuffer payload)
    {
        if (maskBytes == null)
        {
            return;
        }

        int maskInt = this.maskInt;
        int start = payload.position();
        int end = payload.limit();
        int offset = this.maskOffset;
        int remaining;
        while ((remaining = end - start) > 0)
        {
            if (remaining >= 4 && (offset & 3) == 0)
            {
                payload.putInt(start,payload.getInt(start) ^ maskInt);
                start += 4;
                offset += 4;
            }
            else
            {
                payload.put(start,(byte)(payload.get(start) ^ maskBytes[offset & 3]));
                ++start;
                ++offset;
            }
        }
        maskOffset = offset;
    }

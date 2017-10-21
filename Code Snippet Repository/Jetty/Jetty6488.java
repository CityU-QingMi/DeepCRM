    private ByteBuffer asByteBuffer()
    {
        if ((statusCode == StatusCode.NO_CLOSE) || (statusCode == StatusCode.NO_CODE) || (statusCode == (-1)))
        {
            // codes that are not allowed to be used in endpoint.
            return null;
        }

        int len = 2; // status code
        boolean hasReason = (this.reasonBytes != null) && (this.reasonBytes.length > 0);
        if (hasReason)
        {
            len += this.reasonBytes.length;
        }

        ByteBuffer buf = BufferUtil.allocate(len);
        BufferUtil.flipToFill(buf);
        buf.put((byte)((statusCode >>> 8) & 0xFF));
        buf.put((byte)((statusCode >>> 0) & 0xFF));

        if (hasReason)
        {
            buf.put(this.reasonBytes,0,this.reasonBytes.length);
        }
        BufferUtil.flipToFlush(buf,0);

        return buf;
    }

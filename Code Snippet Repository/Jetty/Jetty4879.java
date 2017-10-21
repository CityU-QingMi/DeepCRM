    public static long write(GatheringByteChannel out, ByteBuffer[] buffers, int offset, int length) throws IOException
    {
        long total=0;
        write: while (length>0)
        {
            // Write as much as we can
            long wrote=out.write(buffers,offset,length);

            // If we can't write any more, give up
            if (wrote==0)
                break;

            // count the total
            total+=wrote;

            // Look for unwritten content
            for (int i=offset;i<buffers.length;i++)
            {
                if (buffers[i].hasRemaining())
                {
                    // loop with new offset and length;
                    length=length-(i-offset);
                    offset=i;
                    continue write;
                }
            }
            length=0;
        }

        return total;
    }

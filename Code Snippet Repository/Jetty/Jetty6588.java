    public void copyChunk(byte buf[], int offset, int length)
    {
        if (this.length + length > maxSize)
        {
            throw new MessageTooLargeException("Frame is too large");
        }

        byte copy[] = new byte[length - offset];
        System.arraycopy(buf,offset,copy,0,length);

        chunks.add(copy);
        this.length += length;
    }

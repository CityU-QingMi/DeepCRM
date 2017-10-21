    public void write(char cbuf[], int off, int len) 
    throws IOException 
    {
        ensureOpen();
        
        if (bufferSize == 0) {
            initOut();
            out.write(cbuf, off, len);
            return;
        }
        
        if ((off < 0) || (off > cbuf.length) || (len < 0) ||
                ((off + len) > cbuf.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        } 
        
        if (len >= bufferSize) {
/**/
/**/
/**/
            if (autoFlush)
                flushBuffer();
            else
                bufferOverflow();
            initOut();
            out.write(cbuf, off, len);
            return;
        }
        
        int b = off, t = off + len;
        while (b < t) {
            int d = min(bufferSize - nextChar, t - b);
            System.arraycopy(cbuf, b, cb, nextChar, d);
            b += d;
            nextChar += d;
            if (nextChar >= bufferSize) 
                if (autoFlush)
                    flushBuffer();
                else
                    bufferOverflow();
        }
        
    }

    public void write(char[] cbuf, int off, int len) throws IOException {
        if (writer != null) {
            writer.write(cbuf, off, len);
        } else {
            ensureOpen();
            
            if ((off < 0) || (off > cbuf.length) || (len < 0) ||
                    ((off + len) > cbuf.length) || ((off + len) < 0)) {
                throw new IndexOutOfBoundsException();
            } else if (len == 0) {
                return;
            } 
            
            if (len >= bufferSize - nextChar)
                reAllocBuff (len);
            
            System.arraycopy(cbuf, off, cb, nextChar, len);
            nextChar+=len;
        }
    }

    public void write(String s, int off, int len) throws IOException {
        if (writer != null) {
            writer.write(s, off, len);
        } else {
            ensureOpen();
            if (len >= bufferSize - nextChar)
                reAllocBuff(len);
            
            s.getChars(off, off + len, cb, nextChar);
            nextChar += len;
        }
    }

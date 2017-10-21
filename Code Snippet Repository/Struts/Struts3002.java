    private void reAllocBuff(int len) {
        
        if (bufferSize + len <= cb.length) {
            bufferSize = cb.length;
            return;
        }
        
        if (len < cb.length) {
            len = cb.length;
        }
        
        bufferSize = cb.length + len;
        char[] tmp = new char[bufferSize];
        
        System.arraycopy(cb, 0, tmp, 0, cb.length);
        cb = tmp;
        tmp = null;
        
    }

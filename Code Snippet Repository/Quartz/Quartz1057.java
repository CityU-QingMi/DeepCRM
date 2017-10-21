    @Override
    public void write(byte[] b, int off, int len) {
      if ((off < 0) || (off > b.length) || (len < 0) || ((off + len) > b.length) || ((off + len) < 0)) {
        throw new IndexOutOfBoundsException();
      } else if (len == 0) { return; }
      int newcount = count + len;
      if (newcount > buf.length) {
        char newbuf[] = new char[Math.max(buf.length << 1, newcount)];
        System.arraycopy(buf, 0, newbuf, 0, count);
        buf = newbuf;
      }

      for (int i = 0; i < len; i++) {
        writeChar(b[off + i]);
      }
    }

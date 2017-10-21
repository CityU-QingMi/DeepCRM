    @Override
    public void write(int b) {
      if (count + 1 > buf.length) {
        char[] newbuf = new char[buf.length << 1];
        System.arraycopy(buf, 0, newbuf, 0, count);
        buf = newbuf;
      }

      writeChar(b);
    }

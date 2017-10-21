    public void ensureRoom(int extra) {

        long newcount = count + extra;
        long newsize  = buffer.length;

        if (newcount > Integer.MAX_VALUE) {
            throw new OutOfMemoryError("2GB maximum buffer length exceeded");
        }

        if (newcount > newsize) {
            while (newcount > newsize) {
                newsize *= 2;
            }

            if (newsize > Integer.MAX_VALUE) {
                newsize = Integer.MAX_VALUE;
            }

            byte[] newbuf = new byte[(int) newsize];

            System.arraycopy(buffer, 0, newbuf, 0, count);

            buffer = newbuf;
        }
    }

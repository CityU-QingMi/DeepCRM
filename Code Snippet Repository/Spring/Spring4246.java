    private void getDescriptor(final StringBuilder buf) {
        if (this.buf == null) {
            // descriptor is in byte 3 of 'off' for primitive types (buf ==
            // null)
            buf.append((char) ((off & 0xFF000000) >>> 24));
        } else if (sort == OBJECT) {
            buf.append('L');
            buf.append(this.buf, off, len);
            buf.append(';');
        } else { // sort == ARRAY || sort == METHOD
            buf.append(this.buf, off, len);
        }
    }

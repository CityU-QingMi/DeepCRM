    public ClosableByteArrayOutputStream(int size)
    throws IllegalArgumentException {

        if (size < 0) {
            throw new IllegalArgumentException("Negative initial size: "
                                               + size);    // NOI18N
        }

        buf = new byte[size];
    }

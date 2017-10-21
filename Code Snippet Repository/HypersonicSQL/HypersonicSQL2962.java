    public void resetRow(long filepos, int rowsize) {

        mark = 0;

        reset();

        if (buffer.length < rowsize) {
            buffer = new byte[rowsize];
        }

        filePos   = filepos;
        size      = count = rowsize;
        pos       = 4;
        buffer[0] = (byte) ((rowsize >>> 24) & 0xFF);
        buffer[1] = (byte) ((rowsize >>> 16) & 0xFF);
        buffer[2] = (byte) ((rowsize >>> 8) & 0xFF);
        buffer[3] = (byte) ((rowsize >>> 0) & 0xFF);
    }

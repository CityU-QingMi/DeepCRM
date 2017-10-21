    public void resetBlock(long filepos, int rowsize) {

        mark = 0;

        reset();

        if (buffer.length < rowsize) {
            buffer = new byte[rowsize];
        }

        filePos = filepos;
        size    = count = rowsize;
    }

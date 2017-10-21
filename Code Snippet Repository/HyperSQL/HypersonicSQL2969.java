    public void resetRow(long filepos, int rowsize) {

        if (out != null) {
            out.reset(rowsize);

            buffer = out.getBuffer();
        }

        super.resetRow(filepos, rowsize);
    }

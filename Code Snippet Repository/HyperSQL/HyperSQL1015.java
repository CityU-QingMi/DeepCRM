    public final void writeInt(int v) throws IOException {

        int count = 0;

        tempBuffer[count++] = (byte) (v >>> 24);
        tempBuffer[count++] = (byte) (v >>> 16);
        tempBuffer[count++] = (byte) (v >>> 8);
        tempBuffer[count++] = (byte) v;

        write(tempBuffer, 0, count);
    }

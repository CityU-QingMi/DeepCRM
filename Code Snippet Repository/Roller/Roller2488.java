    public static void copyInputToOutput(InputStream input, OutputStream output)
            throws IOException {
        BufferedInputStream in = new BufferedInputStream(input);
        BufferedOutputStream out = new BufferedOutputStream(output);
        byte buffer[] = new byte[RollerConstants.EIGHT_KB_IN_BYTES];
        for (int count = 0; count != -1;) {
            count = in.read(buffer, 0, RollerConstants.EIGHT_KB_IN_BYTES);
            if (count != -1) {
                out.write(buffer, 0, count);
            }
        }

        try {
            in.close();
            out.close();
        } catch (IOException ex) {
            throw new IOException("Closing file streams, " + ex.getMessage());
        }
    }

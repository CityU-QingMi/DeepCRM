    public static int readLine(InputStream in,
                               OutputStream out) throws IOException {

        int count = 0;

        for (;;) {
            int b = in.read();

            if (b == -1) {
                break;
            }

            count++;

            out.write(b);

            if (b == '\n') {
                break;
            }
        }

        return count;
    }

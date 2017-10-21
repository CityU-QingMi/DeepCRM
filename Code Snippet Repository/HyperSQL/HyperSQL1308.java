    public static String inputStreamToString(InputStream is,
            String encoding) throws IOException {

        HsqlByteArrayOutputStream baOS = new HsqlByteArrayOutputStream(1024);

        while (true) {
            int c = is.read();

            if (c == -1) {
                break;
            }

            baOS.write(c);
        }

        return new String(baOS.getBuffer(), 0, baOS.size(), encoding);
    }

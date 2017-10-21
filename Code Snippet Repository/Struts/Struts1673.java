    public static String readContent(URL url)
        throws Exception {
        if(url == null) {
            throw new Exception("unable to verify a null URL");
        }

        StringBuilder buffer = new StringBuilder(128);
        try (InputStream in = url.openStream()) {
            byte[] buf = new byte[4096];
            int nbytes;

            while ((nbytes = in.read(buf)) > 0) {
                buffer.append(new String(buf, 0, nbytes));
            }
        }

        return buffer.toString();
    }

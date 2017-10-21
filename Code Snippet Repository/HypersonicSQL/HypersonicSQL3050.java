    public static void main(String[] sa) throws Exception {
        if (sa.length < 3) {
            System.err.println("SYNTAX:  " + SqlFileEmbedder.class.getName()
                    + " path/ro/file.rc URLID file1.sql...");
            System.exit(2);
        }
        SqlFileEmbedder embedder =
                new SqlFileEmbedder(new File(sa[0]), sa[1]);
        String[] files = new String[sa.length - 2];
        for (int i = 0; i < sa.length - 2; i++) {
            files[i] = sa[i + 2];
        }
        try {
            embedder.executeFiles(files);
        } finally {
            try {
                embedder.getConn().close();
            } catch (SQLException se) {
                // Purposefully ignoring.
                // We have done what we want and are now going to exit, so
                // who cares.
            }
        }
    }

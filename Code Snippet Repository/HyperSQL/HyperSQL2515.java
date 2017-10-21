    protected void tearDown() throws IOException, SQLException {

        if (baseDir.exists()) {
            rmR(baseDir);

            if (verbose) {
                System.err.println("Tore down");
            }
        }
    }

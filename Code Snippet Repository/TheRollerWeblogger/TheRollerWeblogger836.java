    public static void copyFile(File from, File to) throws IOException {
        InputStream in;
        OutputStream out;

        try {
            in = new FileInputStream(from);
        } catch (IOException ex) {
            throw new IOException("Utilities.copyFile: opening input stream '"
                    + from.getPath() + "', " + ex.getMessage());
        }

        try {
            out = new FileOutputStream(to);
        } catch (Exception ex) {
            try {
                in.close();
            } catch (IOException ex1) {
            }
            throw new IOException("Utilities.copyFile: opening output stream '"
                    + to.getPath() + "', " + ex.getMessage());
        }

        copyInputToOutput(in, out, from.length());
    }

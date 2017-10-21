    protected void rmR(File dir) throws IOException {

        if (!dir.exists()) {
            throw new IOException("Specified dir does not exist: "
                                  + dir.getAbsolutePath());
        }

        File[] children = dir.listFiles();

        for (int i = 0; i < children.length; i++) {
            if (children[i].isDirectory()) {
                rmR(children[i]);
            } else if (!children[i].delete()) {
                throw new IOException("Failed to remove '"
                                      + children[i].getAbsolutePath() + "'");
            }
        }

        if (!dir.delete()) {
            throw new IOException("Failed to remove '" + dir.getAbsolutePath()
                                  + "'");
        }
    }

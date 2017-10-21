    private Directory getFSDirectory(boolean delete) {

        Directory directory = null;

        try {

            directory = FSDirectory.open(new File(indexDir));

            if (delete && directory != null) {
                // clear old files
                String[] files = directory.listAll();
                for (int i = 0; i < files.length; i++) {
                    File file = new File(indexDir, files[i]);
                    if (!file.delete()) {
                        throw new IOException("couldn't delete " + files[i]);
                    }
                }
            }

        } catch (IOException e) {
            mLogger.error("Problem accessing index directory", e);
        }

        return directory;

    }

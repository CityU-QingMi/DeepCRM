    public static boolean deleteOrRenameDatabaseFiles(String dbNamePath) {

        DatabaseFilenameFilter filter = new DatabaseFilenameFilter(dbNamePath);
        File[] fileList = filter.getExistingFileListInDirectory();

        for (int i = 0; i < fileList.length; i++) {
            fileList[i].delete();
        }

        File tempDir = new File(filter.canonicalFile.getPath() + ".tmp");

        if (tempDir.isDirectory()) {
            File[] tempList = tempDir.listFiles();

            if (tempList != null) {
                for (int i = 0; i < tempList.length; i++) {
                    tempList[i].delete();
                }
            }

            tempDir.delete();
        }

        fileList = filter.getExistingMainFileSetList();

        if (fileList.length == 0) {
            return true;
        }

        System.gc();

        for (int i = 0; i < fileList.length; i++) {
            fileList[i].delete();
        }

        fileList = filter.getExistingMainFileSetList();

        for (int i = 0; i < fileList.length; i++) {
            fileList[i].renameTo(
                new File(newDiscardFileName(fileList[i].getPath())));
        }

        return true;
    }

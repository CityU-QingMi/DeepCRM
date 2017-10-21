    void deleteOldDataFiles() {

        if (database.logger.isStoredFileAccess()) {
            return;
        }

        try {
            File   file = new File(database.getCanonicalPath());
            File[] list = file.getParentFile().listFiles();

            if (list == null) {
                return;
            }

            for (int i = 0; i < list.length; i++) {
                if (list[i].getName().startsWith(file.getName())
                        && list[i].getName().endsWith(
                            Logger.oldFileExtension)) {
                    list[i].delete();
                }
            }
        } catch (Throwable t) {}
    }

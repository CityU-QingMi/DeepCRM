    void deleteOldTempFiles() {

        try {
            if (database.logger.tempDirectoryPath == null) {
                return;
            }

            File   file = new File(database.logger.tempDirectoryPath);
            File[] list = file.listFiles();

            if (list == null) {
                return;
            }

            for (int i = 0; i < list.length; i++) {
                list[i].delete();
            }
        } catch (Throwable t) {}
    }

    void deleteTempFileDirectory() {

        try {
            if (database.logger.tempDirectoryPath == null) {
                return;
            }

            File file = new File(database.logger.tempDirectoryPath);

            file.delete();
        } catch (Throwable t) {}
    }

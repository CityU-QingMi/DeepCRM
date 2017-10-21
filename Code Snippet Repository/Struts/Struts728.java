    protected void processFileItemStreamAsFileField(FileItemStream itemStream, String location) {
        // Skip file uploads that don't have a file name - meaning that no file was selected.
        if (itemStream.getName() == null || itemStream.getName().trim().length() < 1) {
            LOG.debug("No file has been uploaded for the field: {}", itemStream.getFieldName());
            return;
        }

        File file = null;
        try {
            // Create the temporary upload file.
            file = createTemporaryFile(itemStream.getName(), location);

            if (streamFileToDisk(itemStream, file)) {
                createFileInfoFromItemStream(itemStream, file);
            }
        } catch (IOException e) {
            if (file != null) {
                try {
                    file.delete();
                } catch (SecurityException se) {
                    LOG.warn("Failed to delete '{}' due to security exception above.", file.getName(), se);
                }
            }
        }
    }

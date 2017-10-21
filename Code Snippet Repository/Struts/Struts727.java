    public void cleanUp() {
        LOG.debug("Performing File Upload temporary storage cleanup.");
        for (String fieldName : fileInfos.keySet()) {
            for (FileInfo fileInfo : fileInfos.get(fieldName)) {
                File file = fileInfo.getFile();
                LOG.debug("Deleting file '{}'.", file.getName());
                if (!file.delete()) {
                    LOG.warn("There was a problem attempting to delete file '{}'.", file.getName());
                }
            }
        }
    }

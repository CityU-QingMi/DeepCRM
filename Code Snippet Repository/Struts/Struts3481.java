    public void cleanUp() {
        Enumeration fileParameterNames = multi.getFileParameterNames();
        while (fileParameterNames != null && fileParameterNames.hasMoreElements()) {
            String inputValue = (String) fileParameterNames.nextElement();
            UploadedFile[] files = getFile(inputValue);
            for (UploadedFile currentFile : files) {
                LOG.debug("Removing file {} {}", inputValue, currentFile);
                if ((currentFile != null) && currentFile.isFile()) {
                    if (!currentFile.delete()) {
                        LOG.warn("Resource Leaking: Could not remove uploaded file [{}]", currentFile.getAbsolutePath());
                    }
                }
            }
        }
    }

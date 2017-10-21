    public UploadedFile[] getFile(String fieldName) {
        List<FileItem> items = files.get(fieldName);

        if (items == null) {
            return null;
        }

        List<UploadedFile> fileList = new ArrayList<>(items.size());
        for (FileItem fileItem : items) {
            File storeLocation = ((DiskFileItem) fileItem).getStoreLocation();
            if (fileItem.isInMemory() && storeLocation != null && !storeLocation.exists()) {
                try {
                    storeLocation.createNewFile();
                } catch (IOException e) {
                    LOG.error("Cannot write uploaded empty file to disk: {}", storeLocation.getAbsolutePath(), e);
                }
            }
            fileList.add(new StrutsUploadedFile(storeLocation));
        }

        return fileList.toArray(new UploadedFile[fileList.size()]);
    }

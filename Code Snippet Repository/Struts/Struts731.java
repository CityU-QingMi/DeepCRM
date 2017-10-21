    protected void createFileInfoFromItemStream(FileItemStream itemStream, File file) {
        // gather attributes from file upload stream.
        String fileName = itemStream.getName();
        String fieldName = itemStream.getFieldName();
        // create internal structure
        FileInfo fileInfo = new FileInfo(file, itemStream.getContentType(), fileName);
        // append or create new entry.
        if (!fileInfos.containsKey(fieldName)) {
            List<FileInfo> infos = new ArrayList<>();
            infos.add(fileInfo);
            fileInfos.put(fieldName, infos);
        } else {
            fileInfos.get(fieldName).add(fileInfo);
        }
    }

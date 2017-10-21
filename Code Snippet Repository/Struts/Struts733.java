    public UploadedFile[] getFile(String fieldName) {
        List<FileInfo> infos = fileInfos.get(fieldName);
        if (infos == null) {
            return null;
        }

        List<UploadedFile> files = new ArrayList<>(infos.size());
        for (FileInfo fileInfo : infos) {
            files.add(new StrutsUploadedFile(fileInfo.getFile()));
        }

        return files.toArray(new UploadedFile[files.size()]);
    }

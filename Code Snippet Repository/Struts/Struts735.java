    public String[] getFilesystemName(String fieldName) {
        List<FileInfo> infos = fileInfos.get(fieldName);
        if (infos == null) {
            return null;
        }

        List<String> names = new ArrayList<>(infos.size());
        for (FileInfo fileInfo : infos) {
            names.add(fileInfo.getFile().getName());
        }

        return names.toArray(new String[names.size()]);
    }

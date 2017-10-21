    public String[] getFileNames(String fieldName) {
        List<FileInfo> infos = fileInfos.get(fieldName);
        if (infos == null) {
            return null;
        }

        List<String> names = new ArrayList<>(infos.size());
        for (FileInfo fileInfo : infos) {
            names.add(getCanonicalName(fileInfo.getOriginalName()));
        }

        return names.toArray(new String[names.size()]);
    }

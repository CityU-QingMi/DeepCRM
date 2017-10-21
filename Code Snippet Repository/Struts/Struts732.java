    public String[] getContentType(String fieldName) {
        List<FileInfo> infos = fileInfos.get(fieldName);
        if (infos == null) {
            return null;
        }

        List<String> types = new ArrayList<>(infos.size());
        for (FileInfo fileInfo : infos) {
            types.add(fileInfo.getContentType());
        }

        return types.toArray(new String[types.size()]);
    }

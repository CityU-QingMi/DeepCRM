    public List<KeyValueObject> getCurrentDirectoryHierarchy() {
        List<KeyValueObject> directoryHierarchy = new ArrayList<KeyValueObject>();

        String fullPath = "/" + this.currentDirectory.getName();
        if (fullPath.length() > 1) {
            String[] directoryNames = fullPath.substring(1).split("/");
            String dirPath = "";
            for (String directoryName : directoryNames) {
                dirPath = dirPath + "/" + directoryName;
                directoryHierarchy.add(new KeyValueObject(dirPath,
                        directoryName));
            }
        }
        return directoryHierarchy;
    }

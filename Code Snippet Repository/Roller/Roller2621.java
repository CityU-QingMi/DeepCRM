    private boolean containsFileWithName(Collection<MediaFile> files,
            String name) {
        for (MediaFile file : files) {
            if (name.equals(file.getName())) {
                return true;
            }
        }
        return false;

    }

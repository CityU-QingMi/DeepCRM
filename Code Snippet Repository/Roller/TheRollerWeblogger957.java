    private boolean containsName(Collection<MediaFileDirectory> directories,
            String name) {
        for (MediaFileDirectory directory : directories) {
            if (name.equals(directory.getName())) {
                return true;
            }
        }
        return false;

    }

    public Set<String> getAbsolutePathsOfLocations() {
        Set<String> paths = new HashSet<String>(mappings.size());
        for (Object value : mappings.values()) {
            String[] location = (String[]) value;

            try {
                File file = FileUtils.toFile(new URL(location[0]));
                paths.add(file.getAbsolutePath());
            } catch (Exception e) {
                //ignore
            }
        }
        return paths;
    }

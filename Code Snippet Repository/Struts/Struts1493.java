    public Set<String> getInstanceNames(Class<?> type) {
        if (DefaultFileManagerFactoryTest.fileManager != null) {
            return new HashSet<String>() {
                {
                    add("dummy");
                }
            };
        }
        return Collections.emptySet();
    }

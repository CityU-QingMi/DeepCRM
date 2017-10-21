    private void loadImplementationsInDirectory(final Test test, final String parent, final File location) {
        final File[] files = location.listFiles();
        if (files == null) {
            return;
        }

        StringBuilder builder;
        for (final File file : files) {
            builder = new StringBuilder();
            builder.append(parent).append('/').append(file.getName());
            final String packageOrClass = parent == null ? file.getName() : builder.toString();

            if (file.isDirectory()) {
                loadImplementationsInDirectory(test, packageOrClass, file);
            } else if (isTestApplicable(test, file.getName())) {
                addIfMatching(test, packageOrClass);
            }
        }
    }

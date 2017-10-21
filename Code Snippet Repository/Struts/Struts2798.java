    private int registerSourceFile(final String file) {
        if (sourceFiles.contains(file)) {
            return -1;
        }

        sourceFiles.add(file);
        this.size++;

        return sourceFiles.size() - 1;
    }

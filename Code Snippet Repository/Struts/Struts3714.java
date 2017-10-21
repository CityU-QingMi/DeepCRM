    private void getAllPaths(File root, List<File> dirs) {
        dirs.add(root);

        if (root.isDirectory()) {
            File[] files = root.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        getAllPaths(file, dirs);
                    }
                }
            }
        }
    }

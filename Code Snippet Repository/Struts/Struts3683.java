    private static File getViewFileInternal(String root, String location, String namespace) {
        StringBuilder filePath = new StringBuilder(root);
        if (!location.startsWith("/")) {
            filePath.append(namespace).append('/');
        }
        filePath.append(location);
        File viewFile = new File(filePath.toString());
        if (viewFile.exists()) {
            return viewFile;
        } else {
            return null;
        }
    }

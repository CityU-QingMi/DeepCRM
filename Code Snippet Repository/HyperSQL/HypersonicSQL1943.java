    public static String makeDirectories(String path) {

        try {
            File file = new File(path);

            file.mkdirs();

            return file.getCanonicalPath();
        } catch (IOException e) {
            return null;
        }
    }

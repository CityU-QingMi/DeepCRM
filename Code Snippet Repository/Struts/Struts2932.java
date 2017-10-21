    private JarFile getJarFile(URL jarFileUrl) throws IOException {
        JarFile jarFile = null;

        if (jarFileUrl != null) {
            JarURLConnection conn = (JarURLConnection) jarFileUrl.openConnection();
            conn.setUseCaches(false);
            conn.connect();
            jarFile = conn.getJarFile();
        }

        return jarFile;
    }

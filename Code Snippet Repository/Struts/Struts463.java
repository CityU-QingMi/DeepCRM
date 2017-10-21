    public static Revision build(URL fileUrl) {
        File file;
        try {
            if (fileUrl != null) {
                file = new File(fileUrl.toURI());
            } else {
                return null;
            }
        } catch (URISyntaxException e) {
            file = new File(fileUrl.getPath());
        }  catch (Throwable t) {
            return null;
        }
        if (file.exists() && file.canRead()) {
            long lastModified = file.lastModified();
            return new FileRevision(file, lastModified);
        }
        return null;
    }

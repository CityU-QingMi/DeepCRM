    public byte[] read(final String pResourceName) {
        FileInputStream fis = null;
        try {
            File file = getFile(pResourceName);
            byte[] data = new byte[(int) file.length()];
            fis = new FileInputStream(file);
            fis.read(data);

            return data;
        } catch (Exception e) {
            LOG.debug("Unable to read file [{}]", pResourceName, e);
            return null;
        } finally {
            closeQuietly(fis);
        }
    }

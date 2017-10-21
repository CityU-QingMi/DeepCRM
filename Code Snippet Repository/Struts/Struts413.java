    public byte[] read(String pResourceName) {
        InputStream in = null;
        try {
            ZipFile jarFile = new ZipFile(file);
            ZipEntry entry = jarFile.getEntry(pResourceName);

            //read into byte array
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            in = jarFile.getInputStream(entry);
            copy(in, out);

            return out.toByteArray();
        } catch (Exception e) {
            LOG.debug("Unable to read file [{}] from [{}]", pResourceName, file.getName(), e);
            return null;
        } finally {
            closeQuietly(in);
        }
    }

    public boolean overQuota(Weblog weblog) {

        String maxDir = WebloggerRuntimeConfig
                .getProperty("uploads.dir.maxsize");

        // maxDirSize in megabytes
        BigDecimal maxDirSize = new BigDecimal(maxDir);

        long maxDirBytes = (long) (RollerConstants.ONE_MB_IN_BYTES * maxDirSize
                .doubleValue());

        try {
            File storageDirectory = this.getRealFile(weblog, null);
            long weblogDirSize = this.getDirSize(storageDirectory, true);

            return weblogDirSize > maxDirBytes;
        } catch (Exception ex) {
            // shouldn't ever happen, this means user's uploads dir is bad
            // rethrow as a runtime exception
            throw new RuntimeException(ex);
        }
    }

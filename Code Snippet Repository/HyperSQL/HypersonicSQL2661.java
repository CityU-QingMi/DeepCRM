    private void closeSessionDataCache() {

        if (resultCache != null) {
            try {
                resultCache.release();
                resultCache.deleteFile();
            } catch (HsqlException e) {}

            resultCache = null;
        }
    }

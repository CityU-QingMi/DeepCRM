    public CachedObject get(RowInputInterface in) {

        try {
            if (isCached) {
                return new RowAVLDisk(this, in);
            }
        } catch (HsqlException e) {
            return null;
        } catch (IOException e1) {
            return null;
        }

        return null;
    }

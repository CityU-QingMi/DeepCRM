    public CachedObject get(RowInputInterface in) {

        try {
            return new RowDiskDataChange(session, this, in);
        } catch (HsqlException e) {
            return null;
        } catch (IOException e1) {
            return null;
        }
    }

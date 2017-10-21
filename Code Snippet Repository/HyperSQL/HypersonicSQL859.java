    public HsqlException[] getAndClearWarnings() {

        if (sqlWarnings == null) {
            return HsqlException.emptyArray;
        }

        HsqlException[] array = new HsqlException[sqlWarnings.size()];

        sqlWarnings.toArray(array);
        sqlWarnings.clear();

        return array;
    }

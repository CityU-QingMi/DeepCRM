    public void addWarning(HsqlException warning) {

        if (sqlWarnings == null) {
            sqlWarnings = new HsqlDeque();
        }

        if (sqlWarnings.size() > 9) {
            sqlWarnings.removeFirst();
        }

        int index = sqlWarnings.indexOf(warning);

        if (index >= 0) {
            sqlWarnings.remove(index);
        }

        sqlWarnings.add(warning);
    }

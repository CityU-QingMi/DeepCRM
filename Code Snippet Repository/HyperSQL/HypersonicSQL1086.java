    public String[] getSQLForTextSource(boolean withHeader) {

        // readonly for TEXT tables only
        if (isText()) {
            HsqlArrayList list = new HsqlArrayList();

            if (isReadOnly) {
                list.add(getSQLForReadOnly());
            }

            // data source
            String dataSource = ((TextTable) this).getDataSourceDDL();

            if (dataSource != null) {
                list.add(dataSource);
            }

            // header
            String header = ((TextTable) this).getDataSourceHeader();

            if (withHeader && header != null && !isReadOnly) {
                list.add(header);
            }

            String[] array = new String[list.size()];

            list.toArray(array);

            return array;
        } else {
            return null;
        }
    }

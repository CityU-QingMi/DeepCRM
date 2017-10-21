    public boolean addRow(Row row) {

        int lookup = list.getLookup(row.getId());

        if (lookup == -1) {
            list.put(row.getId(), row, null);

            size++;

            return true;
        } else {
            if (list.getSecondValueByIndex(lookup) != null) {
                if (session.database.sqlEnforceTDCD) {
                    throw Error.error(ErrorCode.X_27000);
                }

                list.setSecondValueByIndex(lookup, null);
                list.setThirdValueByIndex(lookup, null);

                return true;
            }

            return false;
        }
    }

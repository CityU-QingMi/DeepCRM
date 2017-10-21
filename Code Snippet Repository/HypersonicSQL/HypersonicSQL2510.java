    public Set getUserDefinedPropertyData() {

        Set      set = new HashSet();
        Iterator it  = dbMeta.values().iterator();

        while (it.hasNext()) {
            Object[] row = (Object[]) it.next();

            if (((Integer) row[HsqlProperties.indexType]).intValue()
                    == SQL_PROPERTY) {
                set.add(row);
            }
        }

        return set;
    }

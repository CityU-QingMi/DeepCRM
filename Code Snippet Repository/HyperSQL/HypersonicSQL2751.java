    public RowStoreAVLHybrid(Session session, TableBase table,
                             boolean diskBased) {

        this.table             = table;
        this.maxMemoryRowCount = session.getResultMemoryRowCount();
        this.useDisk           = diskBased;

        if (maxMemoryRowCount == 0) {
            this.useDisk = false;
        }

        if (table.getTableType() == TableBase.RESULT_TABLE) {
            setTimestamp(session.getActionTimestamp());
        }

// test code to force use of cache
/**/
/**/
/**/
/**/
/**/
/**/

//
        resetAccessorKeys(session, table.getIndexList());

        nullsList = new boolean[table.getColumnCount()];
    }

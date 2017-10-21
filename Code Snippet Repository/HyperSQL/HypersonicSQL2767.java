    public void delete(Session session, Row row) {

        NodeAVL node  = ((RowAVL) row).getNode(0);
        int     count = 0;

        while (node != null) {
            count++;

            node = node.nNext;
        }

        if (isCached && row.isMemory() || count != indexList.length) {
            row = ((Table) table).getDeleteRowFromLog(session, row.getData());
        }

        if (row != null) {
            super.delete(session, row);
        }
    }

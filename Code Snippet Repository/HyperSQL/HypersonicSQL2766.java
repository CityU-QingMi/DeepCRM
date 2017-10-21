    public void indexRow(Session session, Row row) {

        NodeAVL node  = ((RowAVL) row).getNode(0);
        int     count = 0;

        while (node != null) {
            count++;

            node = node.nNext;
        }

        if (isCached && row.isMemory() || count != indexList.length) {
            row = (Row) getNewCachedObject(session, row.getData(), true);
        }

        super.indexRow(session, row);
    }

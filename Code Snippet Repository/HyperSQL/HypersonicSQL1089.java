    synchronized Index getIndexForColumns(Session session, int[] cols) {

        int i = bestIndexForColumn[cols[0]];

        if (i > -1) {
            return indexList[i];
        }

        return null;
    }

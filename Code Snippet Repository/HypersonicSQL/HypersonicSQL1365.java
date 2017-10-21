    public void compareRowForChange(Session session, Object[] a, Object[] b,
                                    double[] changes) {

        for (int j = 0; j < colIndex.length; j++) {
            int i = colTypes[j].compare(session, a[colIndex[j]],
                                        b[colIndex[j]]);

            if (i != 0) {
                for (; j < colIndex.length; j++) {
                    changes[j]++;
                }
            }
        }
    }

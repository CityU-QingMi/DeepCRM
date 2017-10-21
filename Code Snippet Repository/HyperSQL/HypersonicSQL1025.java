    public static int compareRows(Session session, Object[] a, Object[] b,
                                  int[] cols, Type[] coltypes) {

        int fieldcount = cols.length;

        for (int j = 0; j < fieldcount; j++) {
            int i = coltypes[cols[j]].compare(session, a[cols[j]], b[cols[j]]);

            if (i != 0) {
                return i;
            }
        }

        return 0;
    }

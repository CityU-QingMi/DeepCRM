    public static Boolean contains(Session session, Object[] a, Type[] ta,
                                   Object[] b, Type[] tb,
                                   boolean pointOfTime) {

        Type commonType = normalizeInput(session, a, ta, b, tb, pointOfTime);

        if (commonType == null) {
            return null;
        }

        int compareStart = commonType.compare(session, a[0], b[0]);
        int compareEnd   = commonType.compare(session, a[1], b[1]);

        if (compareStart <= 0 && compareEnd >= 0) {

            // if the end of the two period are equals, period a does not
            // contains period b if it is defined by a single point in time
            if (pointOfTime) {
                if (compareEnd == 0) {
                    return Boolean.FALSE;
                }
            }

            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public int compare(Session session, Object a, Object b) {

        if (a == b) {
            return 0;
        }

        if (a == null) {
            return -1;
        }

        if (b == null) {
            return 1;
        }

        Object[] arra   = (Object[]) a;
        Object[] arrb   = (Object[]) b;
        int      length = arra.length;

        if (arrb.length < length) {
            length = arrb.length;
        }

        for (int i = 0; i < length; i++) {
            int result = dataType.compare(session, arra[i], arrb[i]);

            if (result != 0) {
                return result;
            }
        }

        if (arra.length > arrb.length) {
            return 1;
        } else if (arra.length < arrb.length) {
            return -1;
        }

        return 0;
    }

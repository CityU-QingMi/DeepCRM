    public int compare(Session session, Object a, Object b) {

        int i = super.compare(session, a, b);

        if (i == 0 && a != null) {
            if (((BinaryData) a).bitLength(null)
                    == ((BinaryData) b).bitLength(null)) {
                return 0;
            }

            return ((BinaryData) a).bitLength(null)
                   > ((BinaryData) b).bitLength(null) ? 1
                                                      : -1;
        }

        return i;
    }

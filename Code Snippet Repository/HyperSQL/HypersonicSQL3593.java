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

        boolean boola = ((Boolean) a).booleanValue();
        boolean boolb = ((Boolean) b).booleanValue();

        return (boola == boolb) ? 0
                                : (boolb ? -1
                                         : 1);
    }

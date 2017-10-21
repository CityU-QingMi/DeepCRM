    public Object concat(Session session, Object a, Object b) {

        if (a == null || b == null) {
            return null;
        }

        int      size  = ((Object[]) a).length + ((Object[]) b).length;
        Object[] array = new Object[size];

        System.arraycopy(a, 0, array, 0, ((Object[]) a).length);
        System.arraycopy(b, 0, array, ((Object[]) a).length,
                         ((Object[]) b).length);

        return array;
    }

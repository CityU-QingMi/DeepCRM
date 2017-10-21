    public Object getField(int i) {

        Object[] current = getCurrent();

        if (current == null) {
            return null;
        }

        return current[i];
    }

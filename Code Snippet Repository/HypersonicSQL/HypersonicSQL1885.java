    public boolean remove(Object o) {

        int i = indexOf(o);

        if (i == -1) {
            return false;
        }

        remove(i);

        return true;
    }

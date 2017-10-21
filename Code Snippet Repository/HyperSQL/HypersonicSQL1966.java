    public boolean containsAll(Collection col) {

        Iterator it = col.iterator();

        while (it.hasNext()) {
            if (contains(it.next())) {
                continue;
            }

            return false;
        }

        return true;
    }

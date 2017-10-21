    public boolean addAll(Collection col) {

        int      oldSize = size();
        Iterator it      = col.iterator();

        while (it.hasNext()) {
            add(it.nextLong());
        }

        return oldSize != size();
    }

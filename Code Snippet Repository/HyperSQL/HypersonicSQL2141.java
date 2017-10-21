    public boolean addAll(Collection col) {

        int      oldSize = size();
        Iterator it      = col.iterator();

        while (it.hasNext()) {
            add(it.nextInt());
        }

        return oldSize != size();
    }

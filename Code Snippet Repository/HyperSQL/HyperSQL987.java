    public boolean addAll(Collection other) {

        boolean  result = false;
        Iterator it     = other.iterator();

        while (it.hasNext()) {
            result = true;

            add(it.next());
        }

        return result;
    }

    public boolean removeAll(Collection c) {

        Iterator it     = c.iterator();
        boolean  result = true;

        while (it.hasNext()) {
            result &= remove(it.next());
        }

        return result;
    }

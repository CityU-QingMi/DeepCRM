    @Override
    public boolean addAll(int index, Collection collection) {
        if (collection == null) {
            throw new NullPointerException("Collection to add is null");
        }

        boolean trim = false;

        if (index >= this.size()) {
            trim = true;
        }

        for (Iterator it = collection.iterator(); it.hasNext(); index++) {
            add(index, it.next());
        }

        if (trim) {
            remove(this.size() - 1);
        }

        return true;
    }

    public boolean hasNext() {
        while (iterators.size() > 0) {
            if (((Iterator) iterators.get(idx)).hasNext()) {
                return true;
            } else {
                iterators.remove(idx);

                if (iterators.size() > 0) {
                    idx = idx % iterators.size();
                }
            }
        }

        return false;
    }

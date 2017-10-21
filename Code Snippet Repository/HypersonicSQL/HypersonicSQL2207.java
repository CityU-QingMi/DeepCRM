    public Object next() {

        // for chained iterators
        if (chained) {
            if (it1 == null) {
                if (it2 == null) {
                    throw new NoSuchElementException();
                }

                if (it2.hasNext()) {
                    return it2.next();
                }

                it2 = null;

                return next();
            } else {
                if (it1.hasNext()) {
                    return it1.next();
                }

                it1 = null;

                return next();
            }
        }

        // for other iterators
        if (hasNext()) {
            return elements[i++];
        }

        throw new NoSuchElementException();
    }

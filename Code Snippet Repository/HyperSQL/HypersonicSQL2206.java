    public boolean hasNext() {

        // for chained iterators
        if (chained) {
            if (it1 == null) {
                if (it2 == null) {
                    return false;
                }

                if (it2.hasNext()) {
                    return true;
                }

                it2 = null;

                return false;
            } else {
                if (it1.hasNext()) {
                    return true;
                }

                it1 = null;

                return hasNext();
            }
        }

        // for other iterators
        if (elements == null) {
            return false;
        }

        for (; notNull && i < elements.length && elements[i] == null; i++) {}

        if (i < elements.length) {
            return true;
        } else {

            // release elements for garbage collection
            elements = null;

            return false;
        }
    }

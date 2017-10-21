    private Collection createCollection(Class toType, Class memberType, int size) {
        Collection result;

        if (toType == Set.class) {
            if (size > 0) {
                result = new HashSet(size);
            } else {
                result = new HashSet();
            }
        } else if (toType == SortedSet.class) {
            result = new TreeSet();
        } else {
            if (size > 0) {
                result = new XWorkList(memberType, size);
            } else {
                result = new XWorkList(memberType);
            }
        }

        return result;
    }

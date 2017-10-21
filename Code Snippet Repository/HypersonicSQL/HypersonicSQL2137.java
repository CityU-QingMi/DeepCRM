    public static OrderedHashSet add(OrderedHashSet first, Object value) {

        if (value == null) {
            return first;
        }

        if (first == null) {
            first = new OrderedHashSet();
        }

        first.add(value);

        return first;
    }

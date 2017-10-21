    public static OrderedHashSet addAll(OrderedHashSet first,
                                        OrderedHashSet second) {

        if (second == null) {
            return first;
        }

        if (first == null) {
            first = new OrderedHashSet();
        }

        first.addAll(second);

        return first;
    }

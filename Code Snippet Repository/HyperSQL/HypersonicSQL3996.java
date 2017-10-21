    static int compare(Object o1, Object o2) {
        // nulls are basically 'illegal' so yes: 
        // we want to throw NPE here if o1 or o2 is null
        if (o1 instanceof Comparable) {
            return (o1.getClass().isAssignableFrom(o2.getClass()))
            ? ((Comparable)o1).compareTo(o2)
            : String.valueOf(o1).compareTo(String.valueOf(o2));
        } else {
            return o1.toString().compareTo(o2.toString());
        }
    }

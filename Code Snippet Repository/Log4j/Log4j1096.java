    protected MapFilter(final Map<String, List<String>> map, final boolean oper, final Result onMatch, final Result onMismatch) {
        super(onMatch, onMismatch);
        this.isAnd = oper;
        Objects.requireNonNull(map, "map cannot be null");

        this.map = new SortedArrayStringMap(map.size());
        for (final Map.Entry<String, List<String>> entry : map.entrySet()) {
            this.map.putValue(entry.getKey(), entry.getValue());
        }
    }

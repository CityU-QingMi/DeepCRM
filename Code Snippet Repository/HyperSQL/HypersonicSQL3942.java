    protected Comparator getComparator(int column) {

        Class columnType = tableModel.getColumnClass(column);
        Comparator comparator =
            (Comparator) columnComparators.get(columnType);

        if (comparator != null) {
            return comparator;
        }

        if (Comparable.class.isAssignableFrom(columnType)) {
            return COMPARABLE_COMPARATOR;
        }

        return LEXICAL_COMPARATOR;
    }

    private Result filter() {
        boolean match = false;
        if (useMap) {
            ReadOnlyStringMap currentContextData = null;
            final IndexedReadOnlyStringMap map = getStringMap();
            for (int i = 0; i < map.size(); i++) {
                if (currentContextData == null) {
                    currentContextData = currentContextData();
                }
                final String toMatch = currentContextData.getValue(map.getKeyAt(i));
                match = toMatch != null && ((List<String>) map.getValueAt(i)).contains(toMatch);
                if ((!isAnd() && match) || (isAnd() && !match)) {
                    break;
                }
            }
        } else {
            match = value.equals(currentContextData().getValue(key));
        }
        return match ? onMatch : onMismatch;
    }

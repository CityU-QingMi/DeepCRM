    protected boolean filter(final MapMessage<?, ?> mapMessage) {
        boolean match = false;
        for (int i = 0; i < map.size(); i++) {
            final String toMatch = mapMessage.get(map.getKeyAt(i));
            match = toMatch != null && ((List<String>) map.getValueAt(i)).contains(toMatch);

            if ((!isAnd && match) || (isAnd && !match)) {
                break;
            }
        }
        return match;
    }

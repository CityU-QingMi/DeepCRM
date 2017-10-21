    public boolean containsKey(Object key) {
        boolean hasKey = super.containsKey(key);

        if (!hasKey) {
            if (valueStack.findValue((String) key) != null) {
                hasKey = true;
            }
        }

        return hasKey;
    }

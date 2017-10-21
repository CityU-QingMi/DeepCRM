    public void removeTransientData() {
        if (!getAllowsTransientData()) { // short circuit...
            return;
        }
    
        String[] keys = getKeys();
        for (int i = 0; i < keys.length; i++) {
            Object o = super.get(keys[i]);
            if (!(o instanceof Serializable)) {
                remove(keys[i]);
            }
        }
    }

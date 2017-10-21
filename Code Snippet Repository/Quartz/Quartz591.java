    @Override
    @SuppressWarnings("")
    public Object clone() {
        DirtyFlagMap<K,V> copy;
        try {
            copy = (DirtyFlagMap<K,V>) super.clone();
            if (map instanceof HashMap) {
                copy.map = (Map<K,V>)((HashMap<K,V>)map).clone();
            }
        } catch (CloneNotSupportedException ex) {
            throw new IncompatibleClassChangeError("Not Cloneable.");
        }

        return copy;
    }

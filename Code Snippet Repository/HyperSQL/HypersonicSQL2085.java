        public Object get(Object key) {

            int lookup = IntValueHashMap.this.getLookup(key, key.hashCode());

            if (lookup < 0) {
                return null;
            } else {
                return IntValueHashMap.this.objectKeyTable[lookup];
            }
        }

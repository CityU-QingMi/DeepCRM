        public Object get(Object key) {

            int lookup = LongValueHashMap.this.getLookup(key, key.hashCode());

            if (lookup < 0) {
                return null;
            } else {
                return LongValueHashMap.this.objectKeyTable[lookup];
            }
        }

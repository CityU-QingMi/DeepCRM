        public Object get(Object key) {

            int lookup = HashMap.this.getLookup(key, key.hashCode());

            if (lookup < 0) {
                return null;
            } else {
                return HashMap.this.objectKeyTable[lookup];
            }
        }

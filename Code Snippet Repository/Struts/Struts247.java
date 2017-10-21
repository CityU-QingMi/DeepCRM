        public V call() {
            // try one more time (a previous future could have come and gone.)
            V value = internalGet(key);
            if (value != null) {
                return value;
            }

            // create value.
            value = create(key);
            if (value == null) {
                throw new NullPointerException("create(K) returned null for: " + key);
            }
            return value;
        }

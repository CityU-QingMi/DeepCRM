        public Object next() throws NoSuchElementException {

            if (lookup == -1) {
                return null;
            }

            Object value = BaseHashMap.this.objectValueTable[lookup];

            while (true) {
                lookup = BaseHashMap.this.hashIndex.getNextLookup(lookup);

                if (lookup == -1
                        || BaseHashMap.this.objectKeyTable[lookup].equals(
                            key)) {
                    break;
                }
            }

            return value;
        }

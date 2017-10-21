        public Object next() throws NoSuchElementException {

            Object value = objectKeyTable[lookup];

            toNextLookup();

            oldKey = value;

            return value;
        }

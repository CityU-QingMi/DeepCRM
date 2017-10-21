        public long nextLong() throws NoSuchElementException {

            if ((keys && !isLongKey) || (!keys && !isLongValue)) {
                throw new NoSuchElementException("Hash Iterator");
            }

            removed = false;

            if (hasNext()) {
                counter++;

                lookup = nextLookup(lookup);

                return keys ? longKeyTable[lookup]
                            : longValueTable[lookup];
            }

            throw new NoSuchElementException("Hash Iterator");
        }

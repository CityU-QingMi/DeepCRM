        public void remove() throws NoSuchElementException {

            if (removed) {
                throw new NoSuchElementException("Hash Iterator");
            }

            counter--;

            removed = true;

            if (BaseHashMap.this.isObjectKey) {
                if (multiValueTable == null) {
                    addOrRemove(0, 0, objectKeyTable[lookup], null, true);
                } else {
                    if (keys) {
                        addOrRemoveMultiVal(0, 0, objectKeyTable[lookup],
                                            null, true, false);
                    } else {
                        addOrRemoveMultiVal(0, 0, objectKeyTable[lookup],
                                            objectValueTable[lookup], false,
                                            true);
                    }
                }
            } else if (isIntKey) {
                addOrRemove(intKeyTable[lookup], 0, null, null, true);
            } else {
                addOrRemove(longKeyTable[lookup], 0, null, null, true);
            }

            if (isList) {
                removeRow(lookup);

                lookup--;
            }
        }

    public Object[] toArray() {

        readLock.lock();

        try {
            if (isEmpty()) {
                return emptyObjectArray;
            }

            Object[] array = new Object[size()];
            int      i     = 0;
            Iterator it    = LongKeyHashMap.this.new BaseHashIterator(false);

            while (it.hasNext()) {
                array[i++] = it.next();
            }

            return array;
        } finally {
            readLock.unlock();
        }
    }

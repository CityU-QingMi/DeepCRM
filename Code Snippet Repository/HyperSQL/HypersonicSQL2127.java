    public Object getKey(long value) {

        BaseHashIterator it = new BaseHashIterator(false);

        while (it.hasNext()) {
            long i = it.nextLong();

            if (i == value) {
                return objectKeyTable[it.getLookup()];
            }
        }

        return null;
    }

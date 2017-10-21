    public Object getKey(int value) {

        BaseHashIterator it = new BaseHashIterator(false);

        while (it.hasNext()) {
            int i = it.nextInt();

            if (i == value) {
                return objectKeyTable[it.getLookup()];
            }
        }

        return null;
    }

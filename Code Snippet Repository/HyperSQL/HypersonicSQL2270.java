    protected Iterator getValuesIterator(Object key, int hash) {

        int lookup = getLookup(key, hash);

        if (valuesIterator == null) {
            valuesIterator = new ValuesIterator();
        }

        valuesIterator.reset(key, lookup);

        return valuesIterator;
    }

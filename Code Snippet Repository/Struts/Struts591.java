    public boolean end(Writer writer, String body) {

        for (Object iteratorEntryObj : _parameters) {
            if (! MakeIterator.isIterable(iteratorEntryObj)) {
                LOG.warn("param with value resolved as {} cannot be make as iterator, it will be ignored and hence will not appear in the merged iterator", iteratorEntryObj);
                continue;
            }
            mergeIteratorFilter.setSource(MakeIterator.convert(iteratorEntryObj));
        }

        mergeIteratorFilter.execute();

        // if id exists, we put it in the stack's context
        putInContext(mergeIteratorFilter);

        mergeIteratorFilter = null;

        return super.end(writer, body);
    }

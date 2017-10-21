    public boolean end(Writer writer, String body) {

        for (Iterator paramEntries = _parameters.iterator(); paramEntries.hasNext(); ) {

            Object iteratorEntryObj = paramEntries.next();
            if (! MakeIterator.isIterable(iteratorEntryObj)) {
                LOG.warn("param with value resolved as {} cannot be make as iterator, it will be ignored and hence will not appear in the merged iterator", iteratorEntryObj);
                continue;
            }
            appendIteratorFilter.setSource(MakeIterator.convert(iteratorEntryObj));
        }

        appendIteratorFilter.execute();

        putInContext(appendIteratorFilter);

        appendIteratorFilter = null;

        return super.end(writer, body);
    }

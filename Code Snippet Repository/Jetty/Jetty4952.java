    public Collection<Part> getParsedParts()
    {
        if (_parts == null)
            return Collections.emptyList();

        Collection<List<Part>> values = _parts.values();
        List<Part> parts = new ArrayList<>();
        for (List<Part> o: values)
        {
            List<Part> asList = LazyList.getList(o, false);
            parts.addAll(asList);
        }
        return parts;
    }

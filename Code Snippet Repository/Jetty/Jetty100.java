    public void addParsedClass (String classname, Resource location)
    {
        List<String> list = new ArrayList<>(1);
        if (location != null)
            list.add(location.toString());

        List<String> existing = _parsedClassNames.putIfAbsent(classname, list);
        if (existing != null)
        {
            existing.addAll(list);
            LOG.warn("{} scanned from multiple locations: {}", classname, existing);
        }
    }

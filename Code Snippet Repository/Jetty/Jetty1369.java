    public E get(PathSpec spec)
    {
        Optional<E> optionalResource = _mappings.stream()
                .filter(mappedResource -> mappedResource.getPathSpec().equals(spec))
                .map(mappedResource -> mappedResource.getResource())
                .findFirst();
        if(!optionalResource.isPresent())
            return null;
                
        return optionalResource.get();
    }

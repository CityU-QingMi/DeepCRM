    public String toString()
    {
        List<String> interested = Collections.emptyList();
        if (_interestedTypes != null)
        {
            interested = new ArrayList<>(_interestedTypes.length);
            for (Class<?> c : _interestedTypes)
                interested.add(c.getName());
        }

        return String.format("ContainerInitializer{%s,interested=%s,applicable=%s,annotated=%s}",_target.getClass().getName(),interested,_applicableTypeNames,_annotatedTypeNames);
    }

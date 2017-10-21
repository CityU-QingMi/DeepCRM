    @Override
    public boolean addAll(Collection collection) {
        if (collection == null) {
            throw new NullPointerException("Collection to add is null");
        }

        for (Object nextElement : collection) {
            add(nextElement);
        }

        return true;
    }

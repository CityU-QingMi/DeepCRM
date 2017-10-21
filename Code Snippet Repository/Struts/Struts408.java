    @Override
    public Object set(int index, Object element) {
        if (index >= this.size()) {
            get(index);
        }

        element = convert(element);

        return super.set(index, element);
    }

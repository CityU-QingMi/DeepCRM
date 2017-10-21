    @Override
    public void add(int index, Object element) {
        if (index >= this.size()) {
            get(index);
        }

        element = convert(element);

        super.add(index, element);
    }

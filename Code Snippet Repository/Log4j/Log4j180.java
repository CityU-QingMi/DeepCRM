    @Override
    public void trim(final int depth) {
        checkInvariants();
        if (depth < 0) {
            throw new IllegalArgumentException("Maximum stack depth cannot be negative");
        }
        if (list == null) {
            return;
        }
        final List<String> copy = new ArrayList<>(list.size());
        final int count = Math.min(depth, list.size());
        for (int i = 0; i < count; i++) {
            copy.add(list.get(i));
        }
        list.clear();
        list.addAll(copy);
    }

    @Override
    public void formatTo(final StringBuilder buffer) {
        buffer.append('[');
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                buffer.append(',').append(' ');
            }
            buffer.append(list.get(i));
        }
        buffer.append(']');
    }

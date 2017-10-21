    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        final MapMessage<?, ?> that = (MapMessage<?, ?>) o;

        return this.data.equals(that.data);
    }

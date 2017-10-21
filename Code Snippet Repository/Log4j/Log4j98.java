    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final ObjectArrayMessage that = (ObjectArrayMessage) o;
        return array == null ? that.array == null : equalObjectsOrStrings(array, that.array);
    }

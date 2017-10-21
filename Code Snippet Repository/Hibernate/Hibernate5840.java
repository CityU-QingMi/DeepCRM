    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LegacyEntity that = (LegacyEntity) o;

        if (primitivePk1 != that.primitivePk1) return false;
        return primitivePk2 == that.primitivePk2;

    }

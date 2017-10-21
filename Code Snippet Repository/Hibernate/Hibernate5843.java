    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NestedLegacyEntity that = (NestedLegacyEntity) o;

        if (legacyEntity != null ? !legacyEntity.equals(that.legacyEntity) : that.legacyEntity != null) return false;
        return modernEntity != null ? modernEntity.equals(that.modernEntity) : that.modernEntity == null;

    }

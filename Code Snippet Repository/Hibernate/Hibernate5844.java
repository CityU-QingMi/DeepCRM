    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NestedLegacyEntityPk that = (NestedLegacyEntityPk) o;

        if (modernEntity != that.modernEntity) return false;
        return legacyEntity != null ? legacyEntity.equals(that.legacyEntity) : that.legacyEntity == null;

    }

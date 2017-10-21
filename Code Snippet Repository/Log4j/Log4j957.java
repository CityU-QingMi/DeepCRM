    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof CustomLevelConfig)) {
            return false;
        }
        final CustomLevelConfig other = (CustomLevelConfig) object;
        return this.intLevel == other.intLevel && this.levelName.equals(other.levelName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModernEntity that = (ModernEntity) o;

        return id == that.id;

    }

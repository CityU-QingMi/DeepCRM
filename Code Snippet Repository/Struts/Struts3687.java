    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Target)) return false;

        final Target target1 = (Target) o;

        if (type != target1.type) return false;
        if (target != null ? !target.equals(target1.target) : target1.target != null) return false;

        return true;
    }

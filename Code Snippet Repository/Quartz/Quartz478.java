    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NotMatcher<?> other = (NotMatcher<?>) obj;
        if (operand == null) {
            if (other.operand != null)
                return false;
        } else if (!operand.equals(other.operand))
            return false;
        return true;
    }

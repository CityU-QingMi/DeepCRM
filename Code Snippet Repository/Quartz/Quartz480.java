    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrMatcher<?> other = (OrMatcher<?>) obj;
        if (leftOperand == null) {
            if (other.leftOperand != null)
                return false;
        } else if (!leftOperand.equals(other.leftOperand))
            return false;
        if (rightOperand == null) {
            if (other.rightOperand != null)
                return false;
        } else if (!rightOperand.equals(other.rightOperand))
            return false;
        return true;
    }

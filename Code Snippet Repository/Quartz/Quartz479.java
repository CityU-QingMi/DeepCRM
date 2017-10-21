    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((leftOperand == null) ? 0 : leftOperand.hashCode());
        result = prime * result
                + ((rightOperand == null) ? 0 : rightOperand.hashCode());
        return result;
    }

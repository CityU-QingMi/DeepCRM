    public boolean equals(Expression other) {

        if (other instanceof FunctionSQL) {
            FunctionSQL o = (FunctionSQL) other;

            return super.equals(other) && funcType == o.funcType;
        }

        return false;
    }

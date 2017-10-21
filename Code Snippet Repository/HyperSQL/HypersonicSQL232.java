    public boolean equals(Expression other) {

        if (other instanceof FunctionSQLInvoked) {
            FunctionSQLInvoked o = (FunctionSQLInvoked) other;

            return super.equals(other) && opType == other.opType
                   && routineSchema == o.routineSchema && routine == o.routine
                   && condition.equals(o.condition);
        }

        return false;
    }

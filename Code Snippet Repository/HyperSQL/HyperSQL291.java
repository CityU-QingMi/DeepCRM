    public Expression duplicate() {

        Expression e = null;

        try {
            e       = (Expression) super.clone();
            e.nodes = (Expression[]) nodes.clone();

            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i] != null) {
                    e.nodes[i] = nodes[i].duplicate();
                }
            }
        } catch (CloneNotSupportedException ex) {
            throw Error.runtimeError(ErrorCode.U_S0500, "Expression");
        }

        return e;
    }

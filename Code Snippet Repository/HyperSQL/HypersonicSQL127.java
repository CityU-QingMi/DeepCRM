    void replaceNode(Expression existing, Expression replacement) {

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == existing) {
                replacement.alias = nodes[i].alias;
                nodes[i]          = replacement;

                return;
            }
        }

        throw Error.runtimeError(ErrorCode.U_S0500, "Expression");
    }

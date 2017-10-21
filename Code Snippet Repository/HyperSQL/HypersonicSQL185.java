    void distributeOr() {

        if (opType != OpTypes.OR) {
            return;
        }

        if (nodes[LEFT].opType == OpTypes.AND) {
            opType = OpTypes.AND;

            Expression temp = new ExpressionLogical(OpTypes.OR,
                nodes[LEFT].nodes[RIGHT], nodes[RIGHT]);

            nodes[LEFT].opType       = OpTypes.OR;
            nodes[LEFT].nodes[RIGHT] = nodes[RIGHT];
            nodes[RIGHT]             = temp;
        } else if (nodes[RIGHT].opType == OpTypes.AND) {
            Expression temp = nodes[LEFT];

            nodes[LEFT]  = nodes[RIGHT];
            nodes[RIGHT] = temp;

            distributeOr();

            return;
        }

        ((ExpressionLogical) nodes[LEFT]).distributeOr();
        ((ExpressionLogical) nodes[RIGHT]).distributeOr();
    }

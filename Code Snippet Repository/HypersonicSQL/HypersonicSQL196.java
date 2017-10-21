    private void changeToRowExpression(int nodeIndex) {

        if (nodes[nodeIndex].opType != OpTypes.ROW) {
            nodes[nodeIndex] = new Expression(OpTypes.ROW,
                                              new Expression[]{
                                                  nodes[nodeIndex] });
            nodes[nodeIndex].nodeDataTypes = new Type[]{
                nodes[nodeIndex].nodes[0].dataType };
        }
    }

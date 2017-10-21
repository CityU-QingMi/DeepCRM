    Type getNodeDataType(int i) {

        if (nodeDataTypes == null) {
            if (i > 0) {
                throw Error.runtimeError(ErrorCode.U_S0500, "Expression");
            }

            return dataType;
        } else {
            return nodeDataTypes[i];
        }
    }

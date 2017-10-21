    private void appendValueAsArray(Object[] valueArray, StringBuilder msg) {
        msg.append("[");
        for (int index = 0; index < valueArray.length; index++) {
            appendValue(valueArray[index]);
            if (hasMoreElements(valueArray, index)) {
                msg.append(", ");
            }
        }
        msg.append("]");
    }

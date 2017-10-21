    @Override
    public String toString() {
        int length = getLength();
        StringBuilder result = new StringBuilder(length * 2);
        for (int i = 0; i < length; ++i) {
            switch (getStep(i)) {
            case ARRAY_ELEMENT:
                result.append('[');
                break;
            case INNER_TYPE:
                result.append('.');
                break;
            case WILDCARD_BOUND:
                result.append('*');
                break;
            case TYPE_ARGUMENT:
                result.append(getStepArgument(i)).append(';');
                break;
            default:
                result.append('_');
            }
        }
        return result.toString();
    }

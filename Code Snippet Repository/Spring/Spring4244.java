    public String getClassName() {
        switch (sort) {
        case VOID:
            return "void";
        case BOOLEAN:
            return "boolean";
        case CHAR:
            return "char";
        case BYTE:
            return "byte";
        case SHORT:
            return "short";
        case INT:
            return "int";
        case FLOAT:
            return "float";
        case LONG:
            return "long";
        case DOUBLE:
            return "double";
        case ARRAY:
            StringBuilder sb = new StringBuilder(getElementType().getClassName());
            for (int i = getDimensions(); i > 0; --i) {
                sb.append("[]");
            }
            return sb.toString();
        case OBJECT:
            return new String(buf, off, len).replace('/', '.').intern();
        default:
            return null;
        }
    }

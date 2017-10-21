    boolean isEqualTo(final Item i) {
        switch (type) {
        case ClassWriter.UTF8:
        case ClassWriter.STR:
        case ClassWriter.CLASS:
        case ClassWriter.MODULE:
        case ClassWriter.PACKAGE:
        case ClassWriter.MTYPE:
        case ClassWriter.TYPE_NORMAL:
            return i.strVal1.equals(strVal1);
        case ClassWriter.TYPE_MERGED:
        case ClassWriter.LONG:
        case ClassWriter.DOUBLE:
            return i.longVal == longVal;
        case ClassWriter.INT:
        case ClassWriter.FLOAT:
            return i.intVal == intVal;
        case ClassWriter.TYPE_UNINIT:
            return i.intVal == intVal && i.strVal1.equals(strVal1);
        case ClassWriter.NAME_TYPE:
            return i.strVal1.equals(strVal1) && i.strVal2.equals(strVal2);
        case ClassWriter.INDY: {
            return i.longVal == longVal && i.strVal1.equals(strVal1)
                    && i.strVal2.equals(strVal2);
        }
        // case ClassWriter.FIELD:
        // case ClassWriter.METH:
        // case ClassWriter.IMETH:
        // case ClassWriter.HANDLE_BASE + 1..9
        default:
            return i.strVal1.equals(strVal1) && i.strVal2.equals(strVal2)
                    && i.strVal3.equals(strVal3);
        }
    }

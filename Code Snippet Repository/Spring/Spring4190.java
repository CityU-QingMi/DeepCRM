    @SuppressWarnings("")
    void set(final int type, final String strVal1, final String strVal2,
            final String strVal3) {
        this.type = type;
        this.strVal1 = strVal1;
        this.strVal2 = strVal2;
        this.strVal3 = strVal3;
        switch (type) {
        case ClassWriter.CLASS:
            this.intVal = 0;     // intVal of a class must be zero, see visitInnerClass
        case ClassWriter.UTF8:
        case ClassWriter.STR:
        case ClassWriter.MTYPE:
        case ClassWriter.MODULE:
        case ClassWriter.PACKAGE:
        case ClassWriter.TYPE_NORMAL:
            hashCode = 0x7FFFFFFF & (type + strVal1.hashCode());
            return;
        case ClassWriter.NAME_TYPE: {
            hashCode = 0x7FFFFFFF & (type + strVal1.hashCode()
                    * strVal2.hashCode());
            return;
        }
        // ClassWriter.FIELD:
        // ClassWriter.METH:
        // ClassWriter.IMETH:
        // ClassWriter.HANDLE_BASE + 1..9
        default:
            hashCode = 0x7FFFFFFF & (type + strVal1.hashCode()
                    * strVal2.hashCode() * strVal3.hashCode());
        }
    }

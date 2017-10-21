    private int readAnnotationValues(int v, final char[] buf,
            final boolean named, final AnnotationVisitor av) {
        int i = readUnsignedShort(v);
        v += 2;
        if (named) {
            for (; i > 0; --i) {
                v = readAnnotationValue(v + 2, buf, readUTF8(v, buf), av);
            }
        } else {
            for (; i > 0; --i) {
                v = readAnnotationValue(v, buf, null, av);
            }
        }
        if (av != null) {
            av.visitEnd();
        }
        return v;
    }

    private void readParameterAnnotations(final MethodVisitor mv,
            final Context context, int v, final boolean visible) {
        int i;
        int n = b[v++] & 0xFF;
        // workaround for a bug in javac (javac compiler generates a parameter
        // annotation array whose size is equal to the number of parameters in
        // the Java source file, while it should generate an array whose size is
        // equal to the number of parameters in the method descriptor - which
        // includes the synthetic parameters added by the compiler). This work-
        // around supposes that the synthetic parameters are the first ones.
        int synthetics = Type.getArgumentTypes(context.desc).length - n;
        AnnotationVisitor av;
        for (i = 0; i < synthetics; ++i) {
            // virtual annotation to detect synthetic parameters in MethodWriter
            av = mv.visitParameterAnnotation(i, "Ljava/lang/Synthetic;", false);
            if (av != null) {
                av.visitEnd();
            }
        }
        char[] c = context.buffer;
        for (; i < n + synthetics; ++i) {
            int j = readUnsignedShort(v);
            v += 2;
            for (; j > 0; --j) {
                av = mv.visitParameterAnnotation(i, readUTF8(v, c), visible);
                v = readAnnotationValues(v + 2, c, true, av);
            }
        }
    }

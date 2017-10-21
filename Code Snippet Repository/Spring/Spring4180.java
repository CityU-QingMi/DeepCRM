    private static int convert(ClassWriter cw, int nInput, Object[] input,
            int[] output) {
        int i = 0;
        for (int j = 0; j < nInput; ++j) {
            if (input[j] instanceof Integer) {
                output[i++] = BASE | ((Integer) input[j]).intValue();
                if (input[j] == Opcodes.LONG || input[j] == Opcodes.DOUBLE) {
                    output[i++] = TOP;
                }
            } else if (input[j] instanceof String) {
                output[i++] = type(cw, Type.getObjectType((String) input[j])
                        .getDescriptor());
            } else {
                output[i++] = UNINITIALIZED
                        | cw.addUninitializedType("",
                                ((Label) input[j]).position);
            }
        }
        return i;
    }

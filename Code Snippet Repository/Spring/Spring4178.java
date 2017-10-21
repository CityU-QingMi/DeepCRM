    final void set(ClassWriter cw, final int nLocal, final Object[] local,
            final int nStack, final Object[] stack) {
        int i = convert(cw, nLocal, local, inputLocals);
        while (i < local.length) {
            inputLocals[i++] = TOP;
        }
        int nStackTop = 0;
        for (int j = 0; j < nStack; ++j) {
            if (stack[j] == Opcodes.LONG || stack[j] == Opcodes.DOUBLE) {
                ++nStackTop;
            }
        }
        inputStack = new int[nStack + nStackTop];
        convert(cw, nStack, stack, inputStack);
        outputStackTop = 0;
        initializationCount = 0;
    }

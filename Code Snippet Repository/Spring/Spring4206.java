    @Override
    public void visitIincInsn(final int var, final int increment) {
        lastCodeOffset = code.length;
        if (currentBlock != null) {
            if (compute == FRAMES || compute == INSERTED_FRAMES) {
                currentBlock.frame.execute(Opcodes.IINC, var, null, null);
            }
        }
        if (compute != NOTHING) {
            // updates max locals
            int n = var + 1;
            if (n > maxLocals) {
                maxLocals = n;
            }
        }
        // adds the instruction to the bytecode of the method
        if ((var > 255) || (increment > 127) || (increment < -128)) {
            code.putByte(196 /* WIDE */).put12(Opcodes.IINC, var)
                    .putShort(increment);
        } else {
            code.putByte(Opcodes.IINC).put11(var, increment);
        }
    }

    @Override
    public void visitInvokeDynamicInsn(final String name, final String desc,
            final Handle bsm, final Object... bsmArgs) {
        lastCodeOffset = code.length;
        Item i = cw.newInvokeDynamicItem(name, desc, bsm, bsmArgs);
        int argSize = i.intVal;
        // Label currentBlock = this.currentBlock;
        if (currentBlock != null) {
            if (compute == FRAMES || compute == INSERTED_FRAMES) {
                currentBlock.frame.execute(Opcodes.INVOKEDYNAMIC, 0, cw, i);
            } else {
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
                if (argSize == 0) {
                    // the above sizes have not been computed yet,
                    // so we compute them...
                    argSize = Type.getArgumentsAndReturnSizes(desc);
                    // ... and we save them in order
                    // not to recompute them in the future
                    i.intVal = argSize;
                }
                int size = stackSize - (argSize >> 2) + (argSize & 0x03) + 1;

                // updates current and max stack sizes
                if (size > maxStackSize) {
                    maxStackSize = size;
                }
                stackSize = size;
            }
        }
        // adds the instruction to the bytecode of the method
        code.put12(Opcodes.INVOKEDYNAMIC, i.index);
        code.putShort(0);
    }

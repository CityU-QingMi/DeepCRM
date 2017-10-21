    @Override
    public void visitMultiANewArrayInsn(final String desc, final int dims) {
        lastCodeOffset = code.length;
        Item i = cw.newStringishItem(ClassWriter.CLASS, desc);
        // Label currentBlock = this.currentBlock;
        if (currentBlock != null) {
            if (compute == FRAMES || compute == INSERTED_FRAMES) {
                currentBlock.frame.execute(Opcodes.MULTIANEWARRAY, dims, cw, i);
            } else {
                // updates current stack size (max stack size unchanged because
                // stack size variation always negative or null)
                stackSize += 1 - dims;
            }
        }
        // adds the instruction to the bytecode of the method
        code.put12(Opcodes.MULTIANEWARRAY, i.index).putByte(dims);
    }

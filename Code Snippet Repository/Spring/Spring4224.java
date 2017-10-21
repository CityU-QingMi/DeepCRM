    @Override
    public void visitIntInsn(final int opcode, final int operand) {
        lastCodeOffset = code.length;
        // Label currentBlock = this.currentBlock;
        if (currentBlock != null) {
            if (compute == FRAMES || compute == INSERTED_FRAMES) {
                currentBlock.frame.execute(opcode, operand, null, null);
            } else if (opcode != Opcodes.NEWARRAY) {
                // updates current and max stack sizes only for NEWARRAY
                // (stack size variation = 0 for BIPUSH or SIPUSH)
                int size = stackSize + 1;
                if (size > maxStackSize) {
                    maxStackSize = size;
                }
                stackSize = size;
            }
        }
        // adds the instruction to the bytecode of the method
        if (opcode == Opcodes.SIPUSH) {
            code.put12(opcode, operand);
        } else { // BIPUSH or NEWARRAY
            code.put11(opcode, operand);
        }
    }

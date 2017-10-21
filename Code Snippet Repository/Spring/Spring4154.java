    public ClassWriter(final int flags) {
        super(Opcodes.ASM6);
        index = 1;
        pool = new ByteVector();
        items = new Item[256];
        threshold = (int) (0.75d * items.length);
        key = new Item();
        key2 = new Item();
        key3 = new Item();
        key4 = new Item();
        this.compute = (flags & COMPUTE_FRAMES) != 0 ? MethodWriter.FRAMES
                : ((flags & COMPUTE_MAXS) != 0 ? MethodWriter.MAXS
                        : MethodWriter.NOTHING);
    }

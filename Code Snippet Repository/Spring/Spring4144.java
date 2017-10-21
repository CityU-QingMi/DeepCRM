    public Object readConst(final int item, final char[] buf) {
        int index = items[item];
        switch (b[index - 1]) {
        case ClassWriter.INT:
            return readInt(index);
        case ClassWriter.FLOAT:
            return Float.intBitsToFloat(readInt(index));
        case ClassWriter.LONG:
            return readLong(index);
        case ClassWriter.DOUBLE:
            return Double.longBitsToDouble(readLong(index));
        case ClassWriter.CLASS:
            return Type.getObjectType(readUTF8(index, buf));
        case ClassWriter.STR:
            return readUTF8(index, buf);
        case ClassWriter.MTYPE:
            return Type.getMethodType(readUTF8(index, buf));
        default: // case ClassWriter.HANDLE_BASE + [1..9]:
            int tag = readByte(index);
            int[] items = this.items;
            int cpIndex = items[readUnsignedShort(index + 1)];
            boolean itf = b[cpIndex - 1] == ClassWriter.IMETH;
            String owner = readClass(cpIndex, buf);
            cpIndex = items[readUnsignedShort(cpIndex + 2)];
            String name = readUTF8(cpIndex, buf);
            String desc = readUTF8(cpIndex + 2, buf);
            return new Handle(tag, owner, name, desc, itf);
        }
    }

    Item newHandleItem(final int tag, final String owner, final String name,
            final String desc, final boolean itf) {
        key4.set(HANDLE_BASE + tag, owner, name, desc);
        Item result = get(key4);
        if (result == null) {
            if (tag <= Opcodes.H_PUTSTATIC) {
                put112(HANDLE, tag, newField(owner, name, desc));
            } else {
                put112(HANDLE,
                        tag,
                        newMethod(owner, name, desc, itf));
            }
            result = new Item(index++, key4);
            put(result);
        }
        return result;
    }

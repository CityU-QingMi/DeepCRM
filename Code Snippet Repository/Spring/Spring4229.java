    @Override
    public void visitRequire(String module, int access, String version) {
        if (requires == null) {
            requires = new ByteVector();
        }
        requires.putShort(cw.newModule(module))
                .putShort(access)
                .putShort(version == null? 0: cw.newUTF8(version));
        requireCount++;
        size += 6;
    }

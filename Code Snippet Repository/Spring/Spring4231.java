    @Override
    public void visitOpen(String packaze, int access, String... modules) {
        if (opens == null) {
            opens = new ByteVector();
        }
        opens.putShort(cw.newPackage(packaze)).putShort(access);
        if (modules == null) {
            opens.putShort(0);
            size += 6;
        } else {
            opens.putShort(modules.length);
            for(String module: modules) {
                opens.putShort(cw.newModule(module));
            }    
            size += 6 + 2 * modules.length; 
        }
        openCount++;
    }

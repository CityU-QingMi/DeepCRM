    @Override
    public void visitExport(String packaze, int access, String... modules) {
        if (exports == null) {
            exports = new ByteVector();
        }
        exports.putShort(cw.newPackage(packaze)).putShort(access);
        if (modules == null) {
            exports.putShort(0);
            size += 6;
        } else {
            exports.putShort(modules.length);
            for(String module: modules) {
                exports.putShort(cw.newModule(module));
            }    
            size += 6 + 2 * modules.length; 
        }
        exportCount++;
    }

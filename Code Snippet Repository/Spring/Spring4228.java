    @Override
    public void visitPackage(String packaze) {
        if (packages == null) { 
            // protect against several calls to visitPackage
            cw.newUTF8("ModulePackages");
            packages = new ByteVector();
            attributeCount++;
            attributesSize += 8;
        }
        packages.putShort(cw.newPackage(packaze));
        packageCount++;
        attributesSize += 2;
    }

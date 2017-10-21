    @Override
    public void visitProvide(String service, String... providers) {
        if (provides == null) {
            provides = new ByteVector();
        }
        provides.putShort(cw.newClass(service));
        provides.putShort(providers.length);
        for(String provider: providers) {
            provides.putShort(cw.newClass(provider));
        }
        provideCount++;
        size += 4 + 2 * providers.length; 
    }

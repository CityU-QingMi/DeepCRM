    @Override
    public AnnotationVisitor visitArray(final String name) {
        ++size;
        if (named) {
            bv.putShort(cw.newUTF8(name));
        }
        // write tag, and reserve space for array size
        bv.put12('[', 0);
        return new AnnotationWriter(cw, false, bv, bv, bv.length - 2);
    }

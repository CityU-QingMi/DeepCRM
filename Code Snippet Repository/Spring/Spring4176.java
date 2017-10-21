    public AnnotationVisitor visitTypeAnnotation(int typeRef,
            TypePath typePath, String desc, boolean visible) {
/**/
/**/
/**/
/**/
/**/
        if (fv != null) {
            return fv.visitTypeAnnotation(typeRef, typePath, desc, visible);
        }
        return null;
    }

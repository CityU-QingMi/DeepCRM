    public AnnotationVisitor visitTypeAnnotation(int typeRef,
            TypePath typePath, String desc, boolean visible) {
/**/
/**/
/**/
/**/
/**/
        if (mv != null) {
            return mv.visitTypeAnnotation(typeRef, typePath, desc, visible);
        }
        return null;
    }

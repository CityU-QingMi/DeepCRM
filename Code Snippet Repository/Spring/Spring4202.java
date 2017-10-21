    public AnnotationVisitor visitTryCatchAnnotation(int typeRef,
            TypePath typePath, String desc, boolean visible) {
/**/
/**/
/**/
/**/
/**/
        if (mv != null) {
            return mv.visitTryCatchAnnotation(typeRef, typePath, desc, visible);
        }
        return null;
    }

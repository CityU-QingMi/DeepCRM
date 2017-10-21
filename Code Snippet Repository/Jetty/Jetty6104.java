    @SuppressWarnings("")
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass)
    {
        if (this.annotations == null)
        {
            return null;
        }

        return (A)this.annotations.get(annotationClass);
    }

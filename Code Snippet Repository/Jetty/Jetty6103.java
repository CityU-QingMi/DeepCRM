    public Param(int idx, Class<?> type, Annotation[] annos)
    {
        this.index = idx;
        this.type = type;
        if (annos != null)
        {
            this.annotations = new HashMap<>();
            for (Annotation anno : annos)
            {
                this.annotations.put(anno.annotationType(),anno);
            }
        }
    }

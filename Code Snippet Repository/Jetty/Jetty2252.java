    public void inject (Object injectable)
    {
        if (_target != null)
        {
            if (_target instanceof Field)
                injectField((Field)_target, injectable);
            else
                injectMethod((Method)_target, injectable);
        }
        else
            throw new IllegalStateException ("No method or field to inject with "+getJndiName());
    }

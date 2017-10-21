    public String toEndPointString()
    {
        Class<?> c=getClass();
        String name=c.getSimpleName();
        while (name.length()==0 && c.getSuperclass()!=null)
        {
            c=c.getSuperclass();
            name=c.getSimpleName();
        }

        return String.format("%s@%h{%s<->%s,%s,fill=%s,flush=%s,to=%d/%d}",
                name,
                this,
                getRemoteAddress(),
                getLocalAddress(),
                _state.get(),
                _fillInterest.toStateString(),
                _writeFlusher.toStateString(),
                getIdleFor(),
                getIdleTimeout());
    }

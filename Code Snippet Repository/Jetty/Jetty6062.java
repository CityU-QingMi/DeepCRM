    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsrExtension that = (JsrExtension) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

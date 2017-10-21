    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("filter configuration:\n");
        sb.append("paths:\n").append(_paths).append("\n");
        sb.append("mime types:\n").append(_mimeTypes).append("\n");
        sb.append("http methods:\n").append(_httpMethods);
        return sb.toString();
    }

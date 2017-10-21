    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append(MultiException.class.getSimpleName());
        if((nested == null) || (nested.size()<=0)) {
            str.append("[]");
        } else {
            str.append(nested);
        }
        return str.toString();
    }

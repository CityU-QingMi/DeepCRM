    @Override
    public String toString() {
        String fields = new LinkedHashMap<String, Object>() {
            {
                put("type", type);
                put("name", name);
                put("implementation", implementation);
                put("scope", scope);
            }
        }.toString();
        StringBuilder sb = new StringBuilder(fields);
        sb.append(super.toString());
        sb.append(" defined at ");
        sb.append(getLocation().toString());
        return sb.toString();
    }

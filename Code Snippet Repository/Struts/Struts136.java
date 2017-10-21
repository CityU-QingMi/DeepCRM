    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ResultConfig)) {
            return false;
        }

        final ResultConfig resultConfig = (ResultConfig) o;

        if ((className != null) ? (!className.equals(resultConfig.className)) : (resultConfig.className != null)) {
            return false;
        }

        if ((name != null) ? (!name.equals(resultConfig.name)) : (resultConfig.name != null)) {
            return false;
        }

        if ((params != null) ? (!params.equals(resultConfig.params)) : (resultConfig.params != null)) {
            return false;
        }

        return true;
    }

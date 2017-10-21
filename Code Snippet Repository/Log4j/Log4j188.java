    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Provider provider = (Provider) o;

        if (priority != null ? !priority.equals(provider.priority) : provider.priority != null) {
            return false;
        }
        if (className != null ? !className.equals(provider.className) : provider.className != null) {
            return false;
        }
        if (loggerContextFactoryClass != null ? !loggerContextFactoryClass.equals(provider.loggerContextFactoryClass) : provider.loggerContextFactoryClass != null) {
            return false;
        }
        return versions != null ? versions.equals(provider.versions) : provider.versions == null;
    }

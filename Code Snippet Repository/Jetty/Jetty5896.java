    protected Entry newEntry(String name, boolean inclusive)
    {
        if (name.startsWith("-"))
            throw new IllegalStateException(name);
        if (name.startsWith("file:"))
            return new LocationEntry(name, inclusive);
        if (name.startsWith("jrt:"))
            return new ModuleEntry(name, inclusive);
        if (name.endsWith("."))
            return new PackageEntry(name, inclusive);
        return new ClassEntry(name,inclusive);
    }

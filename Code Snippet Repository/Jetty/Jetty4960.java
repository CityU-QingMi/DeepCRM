    public MultiReleaseJarFile(File file, int majorVersion, boolean includeDirectories) throws IOException
    {
        if (file==null || !file.exists() || !file.canRead() || file.isDirectory())
            throw new IllegalArgumentException("bad jar file: "+file);

        jarFile = new JarFile(file,true,JarFile.OPEN_READ);
        this.majorVersion = majorVersion;

        Manifest manifest = jarFile.getManifest();
        if (manifest==null)
            multiRelease = false;
        else
            multiRelease = Boolean.valueOf(String.valueOf(manifest.getMainAttributes().getValue("Multi-Release")));

        Map<String,VersionedJarEntry> map = new TreeMap<>();
        jarFile.stream()
                .map(VersionedJarEntry::new)
                .filter(e->(includeDirectories||!e.isDirectory()) && e.isApplicable())
                .forEach(e->map.compute(e.name, (k, v) -> v==null || v.isReplacedBy(e) ? e : v));

        for (Iterator<Map.Entry<String,VersionedJarEntry>> i = map.entrySet().iterator();i.hasNext();)
        {
            Map.Entry<String,VersionedJarEntry> e = i.next();
            VersionedJarEntry entry = e.getValue();

            if (entry.inner)
            {
                VersionedJarEntry outer = map.get(entry.outer);

                if (entry.outer==null || outer.version!= entry.version)
                    i.remove();
            }
        }

        entries = Collections.unmodifiableMap(map);
    }

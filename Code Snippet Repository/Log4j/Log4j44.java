    protected static List<Path> getPaths(final String root) throws IOException {
        final List<Path> paths = new ArrayList<>();
        Files.walkFileTree(Paths.get(root), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
                paths.add(file.toAbsolutePath());
                return FileVisitResult.CONTINUE;
            }
        });
        return paths;
    }
